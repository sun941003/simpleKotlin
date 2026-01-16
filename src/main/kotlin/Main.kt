package org.example

import org.example.solution.Solution
import org.example.source.GeneratorPdf
import testAggregation
import testDataStructures
import testSorting
import testStringProcessing
import testStructureChange
import testTransformation

val solution = Solution()
fun main() {
//    Sub1().hello()
//    Sub1().test()
//    println(Sub1().test(listOf("a", "b", "c")))
//    GeneratorPdf().generate()
    // 테스트 케이스 1
//    val num_list1 = intArrayOf(1, 2, 3, 4, 5)
//    val n1 = 2
//    val result1 = Solution().sol1(num_list1, n1)
//    println(solution.sol1(num_list1,n1).contentToString())
//    println("n개 간격의 원소들")
//    println(solution.sol2(num_list1,n1).contentToString())
//
//    println("이름 배열에서 각 이름이 몇 번 등장했는지 카운트하기")
//    val names = arrayOf("eden", "kiki", "eden", "leo")
//    println(solution.sol3(names))

    //
//    println("===== 1. 데이터 변형 및 필터링 (Map, Filter) =====")
//    testTransformation()
//
//    println("\n===== 2. 집계 및 그룹화 (GroupBy, Count) =====")
//    testAggregation()
//
//    println("\n===== 3. 정렬 (Sorting, Custom Sort) =====")
//    testSorting()
//
//    println("\n===== 4. 문자열 처리 (String Manipulation) =====")
//    testStringProcessing()
//
//    println("\n===== 5. 구조 변경 (Sliding Window, Chunked) =====")
//    testStructureChange()
//
//    println("\n===== 6. 자료구조 (Stack, Queue, PQ) =====")
//    testDataStructures()

    GeneratorPdf().자기소개서()
// 실행 확인을 위한 테스트 코드입니다.
    println("1. 문자열 가공: ${Solution().formatStrangeString("try hello world")}")
    println("2. 배열 필터링: ${Solution().getDivisibleArray(intArrayOf(5, 9, 7, 10), 5).contentToString()}")
    println("3. 정렬 응용: ${Solution().makeLargestNumber(intArrayOf(3, 30, 34, 5, 9))}")
    println("4. 수학(GCD/LCM): ${Solution().getGcdAndLcm(3, 12).contentToString()}")
    println("5. 해시(완주): ${Solution().findDropout(arrayOf("leo", "kiki", "eden"), arrayOf("eden", "kiki"))}")
    println("6. 스택(괄호): ${Solution().isValidParentheses("(())()")}")
    println("7. 내적(reduce): ${Solution().calculateDotProduct(intArrayOf(1, 2, 3, 4), intArrayOf(-3, -1, 0, 2))}")
    println("8. 범위 합계: ${Solution().sumBetweenTwoNumbers(3, 5)}")
    println("9. 진법 변환: ${Solution().reverseTernary(45)}")
    println("10. 시저 암호: ${Solution().caesarCipher("AB", 1)}")

    // 11번 요청 항목 테스트
    val result11 = Solution().sortPaddedDigits(52) // 4자리가 아닌 경우 0052로 취급
    println("11. 숫자 패딩 정렬: 오름차순=${result11.first}, 내림차순=${result11.second}")
}