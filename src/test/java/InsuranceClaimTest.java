
import com.danhlo.UnitTest_Slot4.InsuranceClaim;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceClaimTest {

    private InsuranceClaim claim;

    @BeforeAll
    static void initAll() {
        System.out.println("===> @BeforeAll: Khởi tạo tài nguyên dùng chung cho toàn bộ test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("===> @AfterAll: Giải phóng tài nguyên sau khi chạy tất cả test");
    }

    @BeforeEach
    void setUp() {
        System.out.println("----> @BeforeEach: Chuẩn bị dữ liệu trước mỗi test");
        claim = new InsuranceClaim("C001", 1000.0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("<---- @AfterEach: Kết thúc test, dọn dẹp dữ liệu");
        claim = null;
    }

    // =========================================================
    // FEATURE 1: Constructor Validation + Initialization
    // =========================================================

    @Test
    @DisplayName("Feature1 - Constructor initializes correctly")
    void testConstructorInitializesValues() {
        assertEquals("C001", claim.getClaimId());
        assertEquals(1000.0, claim.getAmount(), 0.001);
        assertEquals("Pending", claim.getClaimStatus());
    }

    @Test
    @DisplayName("Feature1 - Constructor throws exception for invalid amount (negative)")
    void testConstructorInvalidAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new InsuranceClaim("C002", -500));
    }

    @Test
    @DisplayName("Feature1 - Constructor throws exception for null claim ID")
    void testConstructorNullClaimId() {
        assertThrows(IllegalArgumentException.class,
                () -> new InsuranceClaim(null, 1000.0));
    }

    @Test
    @DisplayName("Feature1 - Constructor throws exception for blank claim ID")
    void testConstructorBlankClaimId() {
        assertThrows(IllegalArgumentException.class,
                () -> new InsuranceClaim("   ", 1000.0));
    }

    // =========================================================
    // FEATURE 2: Claim Processing (processClaim lifecycle + validation)
    // =========================================================

    @Test
    @DisplayName("Feature2 - processClaim updates status if current is Pending")
    void testProcessClaimWhenPending() {
        boolean result = claim.processClaim("Approved");
        assertTrue(result);
        assertEquals("Approved", claim.getClaimStatus());
    }

    @Test
    @DisplayName("Feature2 - processClaim returns false if current status is not Pending")
    void testProcessClaimWhenNotPending() {
        claim.processClaim("Approved"); // now status is not Pending
        boolean result = claim.processClaim("Rejected");
        assertFalse(result);
        assertEquals("Approved", claim.getClaimStatus()); // unchanged
    }

    @Test
    @DisplayName("Feature2 - processClaim throws exception for null input (Way A)")
    void testProcessClaimNullInput() {
        assertThrows(IllegalArgumentException.class,
                () -> claim.processClaim(null));
    }

    @Test
    @DisplayName("Feature2 - processClaim throws exception for blank input (Way A)")
    void testProcessClaimBlankInput() {
        assertThrows(IllegalArgumentException.class,
                () -> claim.processClaim("   "));
    }

    // =========================================================
    // FEATURE 3: Payout Calculation + Amount Update + toString
    // =========================================================

    @Test
    @DisplayName("Feature3 - calculatePayout returns correct amount when Approved (85%)")
    void testCalculatePayoutApproved() {
        claim.processClaim("Approved");
        assertEquals(850.0, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("Feature3 - calculatePayout returns 0 when not Approved (Pending)")
    void testCalculatePayoutNotApproved_Pending() {
        // Default status is Pending
        assertEquals(0.0, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("Feature3 - calculatePayout returns 0 when Rejected")
    void testCalculatePayoutNotApproved_Rejected() {
        claim.processClaim("Rejected");
        assertEquals(0.0, claim.calculatePayout(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "Approved,850.0",
            "Rejected,0.0"
    })
    @DisplayName("Feature3 - Parameterized payout test for multiple statuses")
    void testCalculatePayoutVariousStatuses(String status, double expectedPayout) {
        claim.processClaim(status);
        assertEquals(expectedPayout, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("Feature3 - updateClaimAmount updates successfully")
    void testUpdateClaimAmount() {
        claim.updateClaimAmount(2000.0);
        assertEquals(2000.0, claim.getAmount(), 0.001);
    }

    @Test
    @DisplayName("Feature3 - updateClaimAmount throws exception for invalid amount (negative)")
    void testUpdateClaimAmountInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> claim.updateClaimAmount(-1));
    }

    @Test
    @DisplayName("Feature3 - toString returns expected format")
    void testToStringFormat() {
        String output = claim.toString();

        assertTrue(output.contains("InsuranceClaim"));
        assertTrue(output.contains("claimId='C001'"));
        assertTrue(output.contains("amount=1000.0"));
        assertTrue(output.contains("claimStatus='Pending'"));
    }
}