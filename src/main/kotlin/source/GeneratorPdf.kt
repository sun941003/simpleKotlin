package org.example.source
import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.Font
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.pdf.BaseFont
import com.lowagie.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.channels.Channels

class GeneratorPdf {
    fun generate() {
        // 1. 파일 경로 및 이름 설정
        val directoryPath = "/Users/mcs/Downloads"
        val fileName = "경력기술서_최종본_yee.pdf"
        val fullPath = "$directoryPath/$fileName"

        val document = Document()

        try {
            println(">>> PDF 생성 시작: $fullPath")
            PdfWriter.getInstance(document, FileOutputStream(fullPath))
            document.open()

            // 2. 폰트 설정 (macOS 기본 산돌고딕 사용)
            // .ttc 뒤의 ,1 은 폰트 컬렉션 중 Regular/Medium 계열을 선택하기 위함입니다.
            val fontPath = "/System/Library/Fonts/AppleSDGothicNeo.ttc,1"
            val baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)

            // 스타일별 폰트 정의
            val titleFont = Font(baseFont, 20f, Font.BOLD)
            val companyFont = Font(baseFont, 16f, Font.BOLD)
            val sectionHeaderFont = Font(baseFont, 12f, Font.BOLD) // [요약], [주요 프로젝트] 용
            val bodyFont = Font(baseFont, 11f, Font.NORMAL)
            val dateFont = Font(baseFont, 10f, Font.NORMAL) // 날짜 등 부가 정보

            // -------------------------------------------------------
            // 문서 내용 작성
            // -------------------------------------------------------

            // 메인 타이틀
            val title = Paragraph("경력 기술서 (Career Description)", titleFont)
            title.alignment = Paragraph.ALIGN_CENTER
            title.spacingAfter = 20f
            document.add(title)

            // ==========================================
            // 1. 크로스이엔에프
            // ==========================================
            document.add(Paragraph("1. 크로스이엔에프 (Cross ENF) | Android App Developer", companyFont))
            document.add(Paragraph("2022.05 – 재직 중", dateFont))
            document.add(Paragraph(" ", bodyFont)) // 공백

            // [요약]
            document.add(Paragraph("[요약]", sectionHeaderFont))
            val summary1 = """
            - 해외/국내 송금 및 결제 핀테크 앱의 안드로이드 개발을 담당했습니다.
            - 레거시 청산을 위한 아키텍처 개편 작업에 기여했습니다.
            - 금융 보안 규제를 준수하는 다양한 보안/인증 로직을 구현했습니다.
        """.trimIndent()
            document.add(Paragraph(summary1, bodyFont))
            document.add(Paragraph(" ", bodyFont))

            // [주요 프로젝트]
            document.add(Paragraph("[주요 프로젝트]", sectionHeaderFont))
            document.add(Paragraph(" ", bodyFont))

            // 프로젝트 1
            document.add(Paragraph("• 앱 아키텍처 개선 및 모듈화 (2024.04 – 2025.07)", sectionHeaderFont))
            val proj1 = """
             역할: 모듈 분리 작업 수행 및 DI 마이그레이션 참여
             내용:
             - Monolithic 구조를 Multi-module로 분리하여 레이어 의존성 확립
             - Koin에서 Hilt로의 DI 프레임워크 마이그레이션 작업 지원 및 안정화
             - Android Target SDK 35 업데이트 대응
        """.trimIndent()
            document.add(Paragraph(proj1, bodyFont))
            document.add(Paragraph(" ", bodyFont))

            // 프로젝트 2
            document.add(Paragraph("• 글로벌 송금/결제 서비스 고도화 (2022.05 – 2025.10)", sectionHeaderFont))
            val proj2 = """
             역할: 송금/결제 핵심 기능 개발 및 유지보수 (기여도 75%)
             내용:
             - 해외송금 전 과정(계산기~송금완료) E2E 개발 및 환율 에러 핸들링 로직 구현
             - 국내 송금, 오픈뱅킹 계좌 연동, 편의점 바코드 충전 및 QR 결제 기능 구현
             - 사용자 입력 실수 방지를 위한 폼(Form) 검증 로직 및 UX 개선
        """.trimIndent()
            document.add(Paragraph(proj2, bodyFont))
            document.add(Paragraph(" ", bodyFont))

            // 프로젝트 3
            document.add(Paragraph("• 금융 보안 솔루션 연동 및 앱 2.0 리뉴얼", sectionHeaderFont))
            val proj3 = """
             역할: 보안 취약점 점검 대응 및 홈 UI 개편
             내용:
             - 2023년, 2025년 금융보안원 취약점 점검(난독화, 무결성) 대응 및 조치 완료
             - KYC 및 간편 비밀번호 인증 프로세스 고도화
             - 확장성을 고려한 앱 2.0 홈 위젯 UI 구조 설계 및 개발
        """.trimIndent()
            document.add(Paragraph(proj3, bodyFont))
            document.add(Paragraph(" ", bodyFont))
            document.add(Paragraph(" ", bodyFont)) // 회사 간 간격


            // ==========================================
            // 2. 메이크잇
            // ==========================================
            document.add(Paragraph("2. 메이크잇 (MakeIt) | Android App Developer", companyFont))
            document.add(Paragraph("2020.12 – 2022.04 (1년 5개월)", dateFont))
            document.add(Paragraph(" ", bodyFont))

            // [요약]
            document.add(Paragraph("[요약]", sectionHeaderFont))
            val summary2 = """
            - SI/에이전시 환경에서 헬스케어, O2O 등 다양한 도메인의 앱을 개발했습니다.
            - 고객사 요구사항에 맞춘 커스텀 UI 구현과 외부 SDK 연동 경험을 보유하고 있습니다.
        """.trimIndent()
            document.add(Paragraph(summary2, bodyFont))
            document.add(Paragraph(" ", bodyFont))

            // [주요 프로젝트]
            document.add(Paragraph("[주요 프로젝트]", sectionHeaderFont))
            document.add(Paragraph(" ", bodyFont))

            // 프로젝트 1
            document.add(Paragraph("• 헬스케어 서비스 앱 개발 (2021.10 – 2022.04)", sectionHeaderFont))
            val projMake1 = """
             역할: 고객사 전용 헬스케어 SDK 연동 및 데이터 시각화 뷰 구현
             내용: 건강 데이터 동기화 로직 및 맞춤형 푸시 알림 스케줄러 개발
        """.trimIndent()
            document.add(Paragraph(projMake1, bodyFont))
            document.add(Paragraph(" ", bodyFont))

            // 프로젝트 2
            document.add(Paragraph("• 청소 업체 O2O 서비스 앱 (사용자/직원용) (2021.05 – 2021.09)", sectionHeaderFont))
            val projMake2 = """
             역할: 사용자 및 직원용 앱 2종 개발 및 유지보수
             내용: 예약/미션 수행 프로세스 구현, 이미지 리사이징 처리 및 커스텀 프로그레스바 개발
        """.trimIndent()
            document.add(Paragraph(projMake2, bodyFont))
            document.add(Paragraph(" ", bodyFont))

            // 프로젝트 3
            document.add(Paragraph("• 사내 소통 앱 개발 및 레거시 유지보수 (2020.12 – 2022.02)", sectionHeaderFont))
            val projMake3 = """
             역할: 사내 게시판, 차량 배차 관리, QR 스캔 앱 개발 및 유지보수
             내용: 공지사항/투표 기능 구현, MVVM 패턴 학습 및 기존 앱 UI/기능 개선 배포
        """.trimIndent()
            document.add(Paragraph(projMake3, bodyFont))

            println(">>> PDF 생성 완료!")

        } catch (e: Exception) {
            println("!!! 에러 발생: ${e.message}")
            e.printStackTrace()
        } finally {
            if (document.isOpen) {
                document.close()
            }
        }

        // 파일 확인용
        val resultFile = File(fullPath)
        if (resultFile.exists()) {
            println("파일이 정상적으로 생성되었습니다: ${resultFile.absolutePath}")
        }
    }


    fun ge1() {
        // 1. PDF 파일 생성 설정
        val fileName = "자기소개서_Kotlin.pdf"
        val document = Document(PageSize.A4, 50f, 50f, 50f, 50f) // 여백 설정

        try {
            PdfWriter.getInstance(document, FileOutputStream(fileName))
            document.open()

            // 2. 한글 폰트 준비 (나눔고딕 다운로드 및 로드)
            // PDF에서 한글을 쓰려면 폰트 파일이 반드시 필요합니다.
            val fontPath = "NanumGothic.ttf"
            val fontFile = File(fontPath)

            if (!fontFile.exists()) {
                println("한글 폰트 다운로드 중...")
                val website = URL("https://github.com/google/fonts/raw/main/ofl/nanumgothic/NanumGothic-Regular.ttf")
                val rbc = Channels.newChannel(website.openStream())
                val fos = FileOutputStream(fontPath)
                fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
                fos.close()
                println("폰트 다운로드 완료!")
            }

            // 폰트 객체 생성 (BaseFont.IDENTITY_H 가 한글 처리에 중요)
            val baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)

            // 스타일별 폰트 정의
            val titleFont = Font(baseFont, 18f, Font.BOLD)
            val h2Font = Font(baseFont, 14f, Font.BOLD)
            val bodyFont = Font(baseFont, 11f, Font.NORMAL)
            val bulletFont = Font(baseFont, 11f, Font.NORMAL)

            // 3. 내용 작성

            // [제목]
            val title = Paragraph("[자기소개서] 탄탄한 아키텍처 위에서 유연한 서비스를 만드는 5년 차 안드로이드 개발자 문창선입니다.", titleFont)
            title.alignment = Element.ALIGN_CENTER
            title.spacingAfter = 20f
            document.add(title)

            // [본문 데이터]
            val sections = listOf(
                Pair("1. 지원 동기 및 성장 과정: \"폭넓은 경험을 통해 깊이 있는 전문성으로\"",
                    """학부 시절 안드로이드 OS에 매력을 느껴 Java로 개발을 시작했고, 이후 변화하는 생태계에 맞춰 Kotlin을 독학하며 안드로이드 개발자로서의 커리어를 쌓아왔습니다.
지난 5년여간 저는 두 가지 큰 줄기의 경험을 했습니다. 커리어 초기에는 SI 환경에서 헬스케어, O2O, 물류 등 다양한 산업군의 앱을 빠르게 구축하며 비즈니스 요구사항을 기술로 구현하는 '적응력'을 길렀습니다. 이후 최근 3년 9개월간은 단일 서비스를 깊이 있게 운영하며, 대규모 트래픽과 데이터를 다루는 환경에서 앱의 안정성을 높이고 레거시 코드를 개선하는 '기술적 내실'을 다졌습니다.
이제는 특정 도메인에 국한되지 않고, 그동안 쌓아온 아키텍처 설계 능력과 문제 해결력을 바탕으로 귀사의 서비스에 안정적이고 확장 가능한 가치를 더하고자 지원하게 되었습니다."""),

                Pair("2. 핵심 기술 역량: \"유지보수하기 좋은 코드를 위한 끊임없는 개선\"",
                    """저는 단순히 기능을 구현하는 것을 넘어, 장기적인 관점에서 '유지보수 가능한 코드'를 작성하는 데 강점이 있습니다.
* 아키텍처 고도화 및 모듈화 경험: 기존 프로젝트의 구조적 한계를 극복하기 위해 MVVM 패턴을 정착시키고, 더 나아가 클린 아키텍처(Clean Architecture)를 도입하여 레이어를 명확히 분리했습니다. 또한, 프로젝트의 복잡도를 낮추기 위해 모듈화 작업을 수행하며 협업 효율을 높였습니다.
* 최신 기술 스택 도입 및 마이그레이션: 런타임 안정성과 개발 효율성을 위해 의존성 주입(DI) 라이브러리를 Koin에서 Hilt로 마이그레이션 하는 작업을 주도적으로 수행했습니다. 이 과정에서 팀원들과 적극적으로 소통하며 기술 부채를 해결하고, 최신 Android SDK 버전(Target SDK 35) 대응도 놓치지 않고 챙겨왔습니다.
* 안정적인 네트워크 통신 구축: Retrofit과 OkHttp를 활용하여 RESTful API 기반의 서버 통신을 안정적으로 구현했으며, 다양한 예외 상황에 대한 처리를 견고히 하여 앱의 신뢰성을 높였습니다."""),

                Pair("3. 문제 해결 및 협업: \"사용자 중심의 사고와 유연한 커뮤니케이션\"",
                    """개발자는 코드 뒤에 있는 '사용자'를 먼저 생각해야 한다고 믿습니다. 또한, 기획, 디자인, 마케팅 등 유관 부서와의 원활한 소통이 프로젝트의 성공을 좌우한다고 생각합니다.
* 사용자 경험(UX) 최적화: 서버 에러나 네트워크 이슈 등 앱 사용 중 발생할 수 있는 '엣지 케이스'를 방치하지 않고, 사용자가 당황하지 않고 다음 행동을 할 수 있도록 유도하는 흐름을 설계하고 개발했습니다. 또한, 회원가입이나 정보 수정과 같이 복잡할 수 있는 프로세스를 직관적인 UI/UX로 개선하여 사용자 편의성을 높인 경험이 다수 있습니다.
* 비즈니스 목표 달성을 위한 협업: 마케팅 팀의 요청으로 유입 경로 분석 기능을 개발하거나, CS 부서의 피드백을 반영하여 운영 효율을 높이는 기능을 추가하는 등, 비즈니스 목표 달성을 위해 타 부서와 긴밀히 협업했습니다. 외부 솔루션 업체나 보안 관련 부서와의 협업 경험을 통해 까다로운 요구사항도 기술적으로 풀어내는 조율 능력을 갖추고 있습니다."""),

                Pair("4. 다양한 도메인 적응력: \"낯선 환경을 두려워하지 않는 도전 정신\"",
                    """커리어 초반, 다양한 고객사의 프로젝트를 수행하며 어떤 도메인의 서비스라도 빠르게 이해하고 개발할 수 있는 역량을 키웠습니다.
위치 기반 서비스(GPS), 카메라 및 QR 스캔 기능, 외부 하드웨어와의 연동, 복잡한 커스텀 뷰 제작 등 다양한 기술적 요구사항을 소화했습니다. 특히 외부 SDK를 분석하고 기존 앱에 통합하거나, 기기별 화면 대응을 동적으로 처리하는 등 까다로운 이슈들을 해결하며 쌓은 노하우는 귀사의 서비스 개발에도 즉시 기여할 수 있는 자산이 될 것입니다."""),

                Pair("5. 맺음말",
                    """지난 5년이 '기능 구현'과 '구조 개선'을 동시에 배우는 시간이었다면, 앞으로는 이 경험을 바탕으로 '최고의 사용자 경험을 제공하는 완성도 높은 서비스'를 만드는 데 집중하고 싶습니다.
핀테크 도메인에서 검증된 꼼꼼함과 보안 의식, 그리고 SI 시절부터 다져온 빠른 적응력을 바탕으로 귀사의 팀에 빠르게 융화되어 성과를 내는 개발자가 되겠습니다.""")
            )

            // 루프를 돌며 섹션 추가
            for ((secTitle, secContent) in sections) {
                // 소제목 추가
                val h2 = Paragraph(secTitle, h2Font)
                h2.spacingBefore = 15f
                h2.spacingAfter = 10f
                document.add(h2)

                // 본문 내용 줄 단위 처리 (불릿 포인트 들여쓰기 위함)
                val lines = secContent.split("\n")
                for (line in lines) {
                    val p = if (line.trim().startsWith("*")) {
                        // 불릿 포인트 스타일
                        val p = Paragraph(line, bulletFont)
                        p.indentationLeft = 20f // 들여쓰기
                        p.firstLineIndent = -10f // 내어쓰기
                        p
                    } else {
                        // 일반 본문 스타일
                        Paragraph(line, bodyFont)
                    }
                    p.leading = 18f // 줄 간격
                    p.spacingAfter = 5f
                    document.add(p)
                }
            }

            println("PDF 생성 성공: ${File(fileName).absolutePath}")

        } catch (e: Exception) {
            e.printStackTrace()
            println("PDF 생성 실패")
        } finally {
            document.close()
        }
    }
}