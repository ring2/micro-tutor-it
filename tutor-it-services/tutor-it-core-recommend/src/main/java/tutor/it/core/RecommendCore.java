package tutor.it.core;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import tutor.it.common.RestResult;
import tutor.it.generator.entity.OrderCourseRel;
import tutor.it.generator.entity.Orders;
import tutor.it.generator.mapper.OrderCourseRelMapper;
import tutor.it.generator.mapper.OrderMapper;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author :     ring2
 * @date :       2020/4/25 16:04
 * description:
 **/
@Service
public class RecommendCore {

    @Resource
    OrderCourseRelMapper orderCourseRelMapper;

    @Resource
    OrderMapper orderMapper;

    private ArrayList<ArrayList<String>> D = new ArrayList<ArrayList<String>>();// 事务数据库
    private HashMap<ArrayList<String>, Double> C = new HashMap<ArrayList<String>, Double>();// 项目集
    private HashMap<ArrayList<String>, Double> L = new HashMap<ArrayList<String>, Double>();// 候选集
    private final double min_support = 0.04;// 最小支持度
    private final double min_confident = 0.01;// 最小置信度

    // 用于存取候选集每次计算结果，最后计算关联规则，就不用再次遍历事务数据库，这么麻烦了。
    private  HashMap<ArrayList<String>, Double> L_ALL = new HashMap<ArrayList<String>, Double>();


    // 剪枝步，删去C少于最小支持度的元素，形成L
    public void pruning(HashMap<ArrayList<String>, Double> C,
                        HashMap<ArrayList<String>, Double> L) {
        L.clear();
        // 根据项目集生成候选集
        L.putAll(C);
        // 删除少于最小支持度的元素
        ArrayList<ArrayList<String>> delete_key = new ArrayList<ArrayList<String>>();
        for (ArrayList<String> key : L.keySet()) {
            if (L.get(key) < min_support) {
                delete_key.add(key);
            }
        }
        for (int i = 0; i < delete_key.size(); i++) {
            L.remove(delete_key.get(i));
        }
    }

    /**
     * 初始化事务数据库、项目集、候选集
     */
    public void init() {

        D = new ArrayList<>();
        List<OrderCourseRel> orderCourseRels = orderCourseRelMapper.selectList(null);
        List<Orders> orders = orderMapper.selectList(null);
        orders.forEach(orders1 -> {
            ArrayList<String> list1 = new ArrayList<>();
            orderCourseRels.forEach(orderCourseRel -> {
                if (orders1.getId().equals(orderCourseRel.getOrderId())){
                    list1.add(orderCourseRel.getCourseName());
                }
            });
            D.add(list1);
        });
        // 扫描事务数据库。生成项目集，支持度=改元素在事务数据库出现的次数/事务数据库的事务数
        for (int i = 0; i < D.size(); i++) {
            for (int j = 0; j < D.get(i).size(); j++) {
                String[] e = {D.get(i).get(j)};
                ArrayList<String> item = new ArrayList<String>(Arrays.asList(e));
                if (!C.containsKey(item)) {
                    C.put(item, 1.0 / D.size());
                } else {
                    C.put(item, C.get(item) + 1.0 / D.size());
                }
            }
        }

        pruning(C, L);// 剪枝

        L_ALL.putAll(L);

    }

    // 两个整数集求并集
    public ArrayList<String> arrayListUnion(
            ArrayList<String> arraylist1, ArrayList<String> arraylist2) {
        ArrayList<String> arraylist = new ArrayList<String>();
        arraylist.addAll(arraylist1);
        arraylist.addAll(arraylist2);
        arraylist = new ArrayList<String>(new HashSet<String>(arraylist));
        return arraylist;
    }

    /**
     * 迭代求出最终的候选频繁集
     *
     * @param C 完成初始化的项目集
     * @param L 完成初始化的候选集
     * @return 最终的候选频繁集
     */
    public HashMap<ArrayList<String>, Double> iteration(
            HashMap<ArrayList<String>, Double> C,
            HashMap<ArrayList<String>, Double> L) {
        HashMap<ArrayList<String>, Double> L_temp = new HashMap<ArrayList<String>, Double>();// 用于判断是否结束剪枝的临时变量

        int t = 1;// 迭代次数
        while (L.size() > 0) {// 一旦被剪枝后剪干净，剪枝之前则是最终要求的结果。
            t++;
            L_temp.clear();
            L_temp.putAll(L);
            // 一、连接步
            C.clear();
            // 1.将L中的项以一定的规则两两匹配
            ArrayList<ArrayList<String>> L_key = new ArrayList<ArrayList<String>>(
                    L.keySet());
            for (int i = 0; i < L_key.size(); i++) {
                for (int j = i + 1; j < L_key.size(); j++) {
                    ArrayList<String> C_item = new ArrayList<String>();
                    C_item = new ArrayList<String>(arrayListUnion(L_key.get(i),
                            L_key.get(j)));
                    if (C_item.size() == t) {
                        C.put(C_item, 0.0);// 频繁项集的所有非空子集都必须是频繁的
                    }
                }
            }
            // 2.通过扫描D，计算此项的支持度
            for (ArrayList<String> key : C.keySet()) {
                for (int i = 0; i < D.size(); i++) {
                    if (D.get(i).containsAll(key)) {
                        C.put(key, C.get(key) + 1.0 / D.size());
                    }
                }
            }
            // System.out.println(C);
            // 二、剪枝步
            pruning(C, L);
            // System.out.println(L);
            // System.out.println("===");

            L_ALL.putAll(L);
        }

        return L_temp;
    }

    // 求一个集合的所有子集
    public ArrayList<ArrayList<String>> getSubset(ArrayList<String> L) {
        if (L.size() > 0) {
            ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < Math.pow(2, L.size()); i++) {// 集合子集个数=2的该集合长度的乘方
                ArrayList<String> subSet = new ArrayList<String>();
                int index = i;// 索引从0一直到2的集合长度的乘方-1
                for (int j = 0; j < L.size(); j++) {
                    // 通过逐一位移，判断索引那一位是1，如果是，再添加此项
                    if ((index & 1) == 1) {// 位与运算，判断最后一位是否为1
                        subSet.add(L.get(j));
                    }
                    index >>= 1;// 索引右移一位
                }
                result.add(subSet); // 把子集存储起来
            }
            return result;
        } else {
            return null;
        }
    }

    // 判断两个集合相交是否为空
    public boolean intersectionIsNull(ArrayList<String> l1,
                                      ArrayList<String> l2) {
        Set<String> s1 = new HashSet<String>(l1);
        Set<String> s2 = new HashSet<String>(l2);

        s1.retainAll(s2);
        if (s1.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据最终的关联集，根据公式计算出各个关联事件
     */
    public List<String> connection(List<String> list) {
        for (ArrayList<String> key : L.keySet()) {// 对最终的关联集各个事件进行判断
            ArrayList<ArrayList<String>> key_allSubset = getSubset(key);
            // System.out.println(key_allSubset);
            for (int i = 0; i < key_allSubset.size(); i++) {
                ArrayList<String> item_pre = key_allSubset.get(i);
                if (0 < item_pre.size() && item_pre.size() < key.size()) {// 求其非空真子集
                    // 各个非空互补真子集之间形成关联事件
                    double item_pre_support = L_ALL.get(item_pre);
                    for (int j = 0; j < key_allSubset.size(); j++) {
                        ArrayList<String> item_post = key_allSubset.get(j);
                        if (0 < item_post.size()
                                && item_post.size() < key.size()
                                && arrayListUnion(item_pre, item_post).equals(
                                key)
                                && intersectionIsNull(item_pre, item_post)) {
                            double item_post_support = L_ALL.get(item_post);// 互补真子集的支持度比则是事件的置信度
                            double confident = item_pre_support
                                    / item_post_support; // 事件的置信度
                            if (confident > min_confident) {// 如果事件的置信度大于最小置信度
                                list.add(item_pre + "==>" + item_post + "关联概率:" + (float)confident);
                            }
                        }

                    }
                }
            }
        }
        return list;
    }


    public RestResult<List<String>> aprioriAnalysis() {
        // 初始化数据
        init();
        /*
         * System.out.println(D); System.out.println(C); System.out.println(L);
         * System.out.println("===");
         */
        // 剪枝步 连接步迭代
        L = iteration(C, L);
        /*
         * System.out.println(L); System.out.println(L_ALL);
         * System.out.println("===");
         */
        //
        List<String> connection = new ArrayList<>();
        return RestResult.success(connection(connection));
    }
}
