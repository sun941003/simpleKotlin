package org.example.solution

object CodeTest {
    /**
     * 의상 조합의 수를 계산하는 함수
     *
     * @param clothes [[의상 이름, 의상 종류], ...] 형태의 2차원 배열
     * @return 서로 다른 옷의 조합 수
     */
    fun calculateFashionCombinations(clothes: Array<Array<String>>): Int {
        // 1. 종류별로 옷을 그룹화하고 개수를 셉니다.
        // groupBy 결과 예시: { "headgear"=[...2개...], "eyewear"=[...1개...] }
        // values.map { it.size } 결과 예시: [2, 1]
        return clothes.groupBy { it[1] }.values.fold(1) { acc, list ->
            // 2. 각 옷 종류별 개수에 +1을 해서 곱해줍니다 (누적 곱).
            // +1을 하는 이유: 해당 종류의 옷을 '안 입는 경우'를 포함하기 위함입니다.
            // acc: 현재까지의 곱셈 결과 (초기값 1)
            // list.size: 해당 종류 옷의 개수
            acc * (list.size + 1)
        } - 1 // 3. 마지막에 -1을 하는 이유: 모든 종류의 옷을 아무것도 안 입은 상태(알몸)는 제외해야 함
    }
}