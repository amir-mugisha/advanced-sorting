package com.atlab.advanced.sorting.utils;

import com.atlab.advanced.sorting.Actor.Actor;

import java.util.List;
import java.util.Map;

public class QuickSort implements Sort{
    @Override
    public void sort(List<Map<String, Integer>> list, String key) {
        quickSort(list, key, 0, list.size() - 1);
    }
    private void quickSort(List<Map<String, Integer>> list, String key, int low, int high) {
        if (low < high) {
            int pi = partition(list, key, low, high);
            quickSort(list, key, low, pi - 1);
            quickSort(list, key, pi + 1, high);
        }
    }

    private int partition(List<Map<String, Integer>> list, String key, int low, int high) {
        int pivot = list.get(high).get(key);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).get(key) < pivot) {
                i++;
                Map<String, Integer> temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Map<String, Integer> temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
