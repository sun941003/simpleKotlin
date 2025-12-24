package org.example.solution

class Solution {
    /**
     * num_list: 정수 리스트 (실제 코테에서는 보통 IntArray로 주어짐)
     * n: 시작 기준이 되는 순서 (1부터 시작)
     */
    fun sol1(num_list: IntArray, n: Int): IntArray {
        // [해석]
        // 문제에서 'n번째 원소'라고 했으므로, 실제 배열 인덱스는 'n - 1'입니다.
        // 예: n=3 이면, 인덱스는 2 (0, 1, 2...)

        // 방법 1: copyOfRange 사용 (가장 정석적인 배열 자르기 방법)
        // copyOfRange(fromIndex, toIndex) -> fromIndex는 포함, toIndex는 미포함
        return num_list.copyOfRange(n - 1, num_list.size)

        // 방법 2: (참고용) Collection 함수 사용
        // return num_list.drop(n - 1).toIntArray()
    }

    fun sol2(num_list: IntArray, n: Int): IntArray {
        return num_list.slice(0 until num_list.size step(n)).toIntArray()
    }

    // 이름 배열에서 각 이름이 몇 번 등장했는지 카운트하기
    fun sol3(names: Array<String>): Map<String, Int> {
        return names.groupingBy { it }.eachCount()
    }
}