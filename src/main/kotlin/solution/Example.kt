import java.util.* // PriorityQueue, ArrayDeque 등을 위해 필요

// 1. 변형 및 필터링
fun testTransformation() {
    val nums = listOf(1, 2, 3, 4, 5)

    // map: 요소를 변경할 때 (제곱하기)
    val squared = nums.map { it * it }
    println("map (제곱): $squared")

    // filter: 조건에 맞는 것만 남길 때 (짝수만)
    val evens = nums.filter { it % 2 == 0 }
    println("filter (짝수): $evens")

    // mapIndexed: 인덱스가 필요할 때 (인덱스와 값을 곱하기)
    val indexedMap = nums.mapIndexed { index, value -> index * value }
    println("mapIndexed (인덱스*값): $indexedMap")

    // distinct: 중복 제거
    val duplicated = listOf(1, 1, 2, 2, 3)
    println("distinct (중복제거): ${duplicated.distinct()}")
}

// 2. 집계 및 그룹화 (핵심!)
fun testAggregation() {
    val fruits = listOf("apple", "banana", "apple", "cherry", "banana", "apple")

    // groupingBy + eachCount: 빈도수 세기 (가장 중요!)
    val counts: Map<String, Int> = fruits.groupingBy { it }.eachCount()
    println("빈도수 세기: $counts") // 출력: {apple=3, banana=2, cherry=1}

    // groupBy: 조건에 따라 그룹핑 (길이별로 묶기)
    val groupedByLength: Map<Int, List<String>> = fruits.groupBy { it.length }
    println("길이별 그룹핑: $groupedByLength")

    // fold: 누적 연산 (모든 숫자의 합 구하기, 초기값 0)
    val numbers = listOf(1, 2, 3, 4, 5)
    val sum = numbers.fold(0) { acc, i -> acc + i }
    println("fold (누적합): $sum")
}

// 3. 정렬
fun testSorting() {
    data class Student(val name: String, val score: Int)
    val students = listOf(
        Student("철수", 80),
        Student("영희", 100),
        Student("민수", 80)
    )

    // sortedBy: 점수 기준 오름차순
    println("점수순 정렬: ${students.sortedBy { it.score }}")

    // sortedByDescending: 점수 기준 내림차순
    println("점수 역순 정렬: ${students.sortedByDescending { it.score }}")

    // sortedWith + compareBy: 다중 조건 정렬 (점수는 내림차순, 점수 같으면 이름 오름차순)
    // 코딩테스트 단골 패턴입니다.
    val multiSort = students.sortedWith(
        compareByDescending<Student> { it.score } // 1순위: 점수 높은 순
            .thenBy { it.name }                   // 2순위: 이름 가나다 순
    )
    println("다중 조건 정렬: $multiSort")
}

// 4. 문자열 처리
fun testStringProcessing() {
    val rawString = " 1, 2, 3, 4, 5 "

    // trim, split, map, joinToString 콤보
    // 상황: "공백 제거 -> 쉼표로 자르기 -> 숫자로 변환 -> 10 곱하기 -> 하이픈으로 연결"
    val result = rawString.trim()
        .split(",")
        .map { it.trim().toInt() * 10 }
        .joinToString("-")

    println("문자열 처리 콤보: $result") // 출력: 10-20-30-40-50

    // replace: 문자열 치환
    val text = "Hello World"
    println("Replace: ${text.replace("World", "Kotlin")}")
}

// 5. 구조 변경 (알고리즘 구현 꿀팁)
fun testStructureChange() {
    val nums = (1..10).toList()

    // chunked: 데이터를 n개씩 덩어리로 자름 (페이징 처리 등에 유용)
    println("chunked(3): ${nums.chunked(3)}")
    // [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]

    // windowed: 슬라이딩 윈도우 (겹치면서 지나감)
    // 3개씩 묶되, 1칸씩 이동
    println("windowed(3, 1): ${nums.windowed(3, 1)}")
    // [[1, 2, 3], [2, 3, 4], [3, 4, 5] ...]

    // zip: 두 리스트를 짝지음
    val names = listOf("A", "B", "C")
    val scores = listOf(100, 90, 80)
    println("zip: ${names.zip(scores)}") // [(A, 100), (B, 90), (C, 80)]
}

// 6. 자료구조 (Stack, Queue, PriorityQueue)
fun testDataStructures() {
    // 1. Stack & Queue 대용 -> ArrayDeque (Java의 Stack 클래스는 쓰지 마세요)
    val deque = ArrayDeque<Int>()

    // 스택처럼 쓰기 (LIFO: Last In First Out)
    deque.addLast(1)
    deque.addLast(2)
    val popVal = deque.removeLast() // 2가 나옴
    println("Stack Pop: $popVal")

    // 큐처럼 쓰기 (FIFO: First In First Out) - BFS 문제 필수
    deque.clear()
    deque.addLast(1)
    deque.addLast(2)
    val pollVal = deque.removeFirst() // 1이 나옴
    println("Queue Poll: $pollVal")

    // 2. PriorityQueue (우선순위 큐) - 힙(Heap), 다익스트라
    // 기본은 작은 숫자가 먼저 나옴 (오름차순)
    val pq = PriorityQueue<Int>()
    pq.addAll(listOf(5, 1, 3))
    println("PQ Poll: ${pq.poll()}") // 1 (가장 작은 수)

    // 큰 숫자가 먼저 나오게 하려면 (내림차순)
    val maxPq = PriorityQueue<Int>(Collections.reverseOrder())
    maxPq.addAll(listOf(5, 1, 3))
    println("Max PQ Poll: ${maxPq.poll()}") // 5 (가장 큰 수)
}