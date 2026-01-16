package org.example.solution

import kotlin.math.max
import kotlin.math.min

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

    // =================================================================================
// 1. 문자열 처리 (String Manipulation)
// =================================================================================
    /*
     * 문제: 공백을 기준으로 단어를 나누고, 각 단어의 짝수번째 알파벳은 대문자, 홀수번째는 소문자로 변환
     * 테스트 값: "try hello world" -> "TrY HeLlO WoRlD"
     * 가이드: split으로 쪼개고, 인덱스(mapIndexed)를 활용하여 대소문자 변환 후 joinToString으로 합침
     */
    fun formatStrangeString(s: String): String {
        return s.split(" ").joinToString(" ") { word ->
            word.mapIndexed { index, char ->
                if (index % 2 == 0) char.uppercaseChar() else char.lowercaseChar()
            }.joinToString("")
        }
    }

    // =================================================================================
// 2. 배열과 리스트 (Array & List)
// =================================================================================
    /*
     * 문제: 배열(arr)의 요소 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬하여 반환 (없으면 -1)
     * 테스트 값: arr=[5, 9, 7, 10], divisor=5 -> [5, 10]
     * 가이드: filter로 걸러내고, sorted로 정렬. 결과가 비었는지(isEmpty) 체크하여 분기 처리
     */
    fun getDivisibleArray(arr: IntArray, divisor: Int): IntArray {
        val result = arr.filter { it % divisor == 0 }.sorted()
        return if (result.isEmpty()) intArrayOf(-1) else result.toIntArray()
    }

    // =================================================================================
// 3. 정렬 심화 (Sorting)
// =================================================================================
    /*
     * 문제: 숫자를 이어 붙여 만들 수 있는 '가장 큰 수'를 문자열로 반환
     * 테스트 값: [6, 10, 2] -> "6210" (6102보다 큼)
     * 가이드: 숫자를 문자로 바꾼 뒤, 두 문자를 앞뒤로 합쳐봤을 때(a+b vs b+a) 큰 순서대로 내림차순 정렬
     */
    fun makeLargestNumber(numbers: IntArray): String {
        val answer = numbers.map { it.toString() }
            .sortedWith { a, b -> (b + a).compareTo(a + b) } // 내림차순 비교 로직
            .joinToString("")

        // "000" 처럼 0으로만 구성된 경우 "0" 하나만 리턴
        return if (answer.startsWith("0")) "0" else answer
    }

    // =================================================================================
// 4. 수학 - 최대공약수와 최소공배수 (Math)
// =================================================================================
    /*
     * 문제: 두 수의 최대공약수(GCD)와 최소공배수(LCM)를 배열로 반환
     * 테스트 값: n=3, m=12 -> [3, 12]
     * 가이드: 유클리드 호제법(재귀)으로 GCD를 구하고, (n*m / GCD) 공식을 이용해 LCM 계산
     */
    fun getGcdAndLcm(n: Int, m: Int): IntArray {
        fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

        val g = gcd(n, m)
        val l = (n.toLong() * m.toLong() / g).toInt() // 곱셈 오버플로우 방지 차원에서 Long 형변환 권장
        return intArrayOf(g, l)
    }

    // =================================================================================
// 5. 해시/맵 (Map & Hashing)
// =================================================================================
    /*
     * 문제: 마라톤 참가자 중 완주하지 못한 단 한 명의 이름을 반환 (동명이인 있을 수 있음)
     * 테스트 값: participant=["leo", "kiki", "eden"], completion=["eden", "kiki"] -> "leo"
     * 가이드: HashMap에 이름을 Key, 인원수를 Value로 저장 후 완주자를 뺌(Value 감소)
     */
    fun findDropout(participant: Array<String>, completion: Array<String>): String {
        val map = HashMap<String, Int>()
        participant.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        completion.forEach { map[it] = map[it]!! - 1 }

        // value가 0이 아닌(남아있는) 사람이 범인
        return map.filter { it.value > 0 }.keys.first()
    }

    // =================================================================================
// 6. 스택/큐 (Stack & Queue)
// =================================================================================
    /*
     * 문제: 괄호가 바르게 짝지어졌는지 확인
     * 테스트 값: "(())()" -> true, "(()(" -> false
     * 가이드: ArrayDeque를 스택으로 사용. '('면 넣고, ')'면 뺀다. 뺄 게 없거나 남으면 false
     */
    fun isValidParentheses(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        for (char in s) {
            if (char == '(') {
                stack.addLast(char)
            } else {
                if (stack.isEmpty()) return false
                stack.removeLast()
            }
        }
        return stack.isEmpty()
    }

    // =================================================================================
// 7. 컬렉션 연산 (Functional Programming)
// =================================================================================
    /*
     * 문제: 두 배열의 내적(각 자리수의 곱의 합)을 구함
     * 테스트 값: a=[1,2,3,4], b=[-3,-1,0,2] -> 3
     * 가이드: zip으로 두 리스트를 묶고, fold(누적 연산)를 사용하여 합계 계산
     */
    fun calculateDotProduct(a: IntArray, b: IntArray): Int {
        return a.zip(b).fold(0) { acc, (aVal, bVal) -> acc + (aVal * bVal) }
    }

    // =================================================================================
// 8. 범위와 등차수열 (Range)
// =================================================================================
    /*
     * 문제: 두 정수 a, b 사이에 속한 모든 정수의 합을 리턴 (a, b 대소관계 모름)
     * 테스트 값: a=3, b=5 -> 12 (3+4+5)
     * 가이드: min, max로 범위를 확정하고 range(..)를 만든 뒤 sum()
     */
    fun sumBetweenTwoNumbers(a: Int, b: Int): Long {
        val start = min(a, b)
        val end = max(a, b)
        return (start.toLong()..end.toLong()).sum()
    }

    // =================================================================================
// 9. 진법 변환 (Type Conversion)
// =================================================================================
    /*
     * 문제: 자연수 n을 3진법으로 뒤집은 후 다시 10진법으로 표현
     * 테스트 값: n=45 -> 10진수 7 (45 -> 3진법 "1200" -> 뒤집으면 "0021" -> 10진수 7)
     * 가이드: toString(radix)로 진법 변환, reversed()로 뒤집기, toInt(radix)로 복구
     */
    fun reverseTernary(n: Int): Int {
        return n.toString(3).reversed().toInt(3)
    }

    // =================================================================================
// 10. 문자 제어 (Char Control - Caesar Cipher)
// =================================================================================
    /*
     * 문제: 각 알파벳을 n만큼 뒤로 민 암호문 생성 (z 다음은 a)
     * 테스트 값: s="AB", n=1 -> "BC"
     * 가이드: Char의 아스키 코드 계산. 대문자/소문자 범위를 기준으로 나머지 연산(%) 활용
     */
    fun caesarCipher(s: String, n: Int): String {
        return s.map { char ->
            when {
                char.isLowerCase() -> 'a' + (char - 'a' + n) % 26
                char.isUpperCase() -> 'A' + (char - 'A' + n) % 26
                else -> char
            }
        }.joinToString("")
    }

    // =================================================================================
// 11. (요청 항목) 숫자 자릿수 패딩 및 정렬
// =================================================================================
    /*
     * 문제: 정수 n을 입력받아 4자리 문자열로 맞춤(앞에 0 채움). 그 후 각 자릿수를 오름차순, 내림차순 정렬하여 반환
     * 테스트 값: 52 -> "0052"로 변환 -> 오름차순 "0025", 내림차순 "5200"
     * 응용: 카프리카 상수(Kaprekar's constant) 문제 등의 전처리 단계에서 주로 사용됨
     * 가이드:
     * 1. toString().padStart(4, '0')으로 0 채우기
     * 2. toCharArray().sorted()로 정렬
     * 3. joinToString("")으로 다시 문자열 결합
     */
    fun sortPaddedDigits(n: Int): Pair<String, String> {
        // 1. 4자리로 맞추기 (빈 앞자리는 '0'으로 채움)
        val paddedString = n.toString().padStart(4, '0')

        // 2. 오름차순 정렬
        val ascending = paddedString.toCharArray().sorted().joinToString("")

        // 3. 내림차순 정렬
        val descending = paddedString.toCharArray().sortedDescending().joinToString("")

        return Pair(ascending, descending)
    }
}