/**
 * Copyright (c) 2019 landon30
 */

package com.landon30.bucks.concurrent;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 测试排序set
 * 
 * 问题描述 <br>
 * 1. 排序集合如ConcurrentSkipListSet或者TreeSet <br>
 * 2. 依次添加排序的Element对象 <br>
 * 3. 改变某一个Element对象的用于排序的值 <br>
 * 4. remove该元素，在某些情况下有时候会成功，有时候会失败，比较奇怪 <br>
 * 
 * 测试步骤 <br>
 * 1. 本例可以将循环调大 <br>
 * 2. 可分别测试 element2.setScore(70)和element2.setScore(90) <br>
 * 
 * ref:https://stackoverflow.com/questions/44026315/issue-with-concurrentskiplistset-remove
 *
 * @date 2019-11-29
 * @author landon30
 */
public class SortedSetExample {
    public static void main(String[] args) {
        System.out.println("---ConcurrentSkipListSet test---");

        for (int i = 0; i < 10; i++) {
            ConcurrentSkipListSet<Element> set = new ConcurrentSkipListSet<>();

            Element element0 = new Element(1, 60);
            Element element1 = new Element(2, 80);
            Element element2 = new Element(3, 100);

            set.add(element0);
            set.add(element1);
            set.add(element2);

            // 1. 在add之后，改变某个元素内用于排序的值
            // 2. remove元素A，有时候会成功,有时候会失败，比较奇怪（可多次循环），失败的时候排序结果也不正确
            // 3. TODO 看看源代码实现
            // 4. 如果改为90则无问题
            element2.setScore(70);
            boolean isRemove = set.remove(element2);
            if (!isRemove) {
                System.out.println(isRemove);
                System.out.println(set.size());
                System.out.println(set);
            }
        }

        System.out.println("--treeset test--");

        // 结果同上
        for (int i = 0; i < 10; i++) {
            TreeSet<Element> set = new TreeSet<>();

            Element element0 = new Element(1, 60);
            Element element1 = new Element(2, 80);
            Element element2 = new Element(3, 100);

            set.add(element0);
            set.add(element1);
            set.add(element2);

            element2.setScore(70);
            boolean isRemove = set.remove(element2);
            if (!isRemove) {
                System.out.println(isRemove);
                System.out.println(set.size());
                System.out.println(set);
            }
        }
    }

    private static class Element implements Comparable<Element> {
        private int id;
        private int score;

        public Element(int id, int score) {
            this.id = id;
            this.score = score;
        }

        /**
         * @param score the score to set
         */
        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public int compareTo(Element o) {
            if (score > o.score) {
                return -1;
            }

            if (score < o.score) {
                return 1;
            }

            return Integer.compare(id, o.id);
        }

        @Override
        public String toString() {
            return "Element [id=" + id + ", score=" + score + "]";
        }
    }
}
