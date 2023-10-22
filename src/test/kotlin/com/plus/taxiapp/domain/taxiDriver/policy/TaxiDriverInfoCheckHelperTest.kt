
import com.plus.taxiapp.domain.taxiDriver.policy.TaxiDriverInfoCheckHelper
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TaxiDriverInfoCheckHelperTest {

    @Nested
    @DisplayName("택시 정보 검증 성공 테스트")
    inner class ValidateTaxiInfoSuccess {

        @Test
        fun `정책에 맞는 택시 정보가 입력되면 에러가 발생하지 않는다`() {
            TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled("서울 28바 2311", "에쿠스")
            TaxiDriverInfoCheckHelper.checkTaxiNumberFollowsFormat("서울 28바 2311")
        }
    }

    @Nested
    @DisplayName("택시 정보 검증 실패 테스트")
    inner class ValidateTaxiInfoFailure {

        @Test
        fun `택시 번호가 비어있으면 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled("", "에쿠스")
            }
            assertEquals("Taxi Number is Required", exception.message)
        }

        @Test
        fun `택시 번호가 공백으로 이루어져있는 경우 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled(" ", "에쿠스")
            }
            assertEquals("Taxi Number is Required", exception.message)
        }

        @Test
        fun `택시 모델이 비어있으면 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled("서울 28바 2311", "")
            }
            assertEquals("Taxi Model is Required", exception.message)
        }

        @Test
        fun `택시 모델이 공백으로 이루어져 있는 경우 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriverInfoCheckHelper.checkTaxiDetailsAreFilled("서울 28바 2311", "")
            }
            assertEquals("Taxi Model is Required", exception.message)
        }

        @Test
        fun `택시 번호 형식이 올바르지 않으면 예외가 발생한다`() {
            val exception = assertThrows<IllegalArgumentException> {
                TaxiDriverInfoCheckHelper.checkTaxiNumberFollowsFormat("서울28바2311****")
            }
            assertEquals("Taxi Number is not in the right format", exception.message)
        }
    }
}
