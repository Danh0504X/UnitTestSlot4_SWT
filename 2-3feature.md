**Feature 1 – Constructor Validation (InsuranceClaim(String claimId, double claimAmount))**



Nếu claimId là null hoặc rỗng/blank → ném IllegalArgumentException.



Nếu claimAmount < 0 → ném IllegalArgumentException.



Nếu hợp lệ → tạo claim với claimStatus = "Pending".



Test cases:



Tạo object hợp lệ → kiểm tra claimId, amount, claimStatus.



* claimId = null → expect exception.
* 
* claimId = " " → expect exception.
* 
* claimAmount = -500 → expect exception.



**Feature 2 – Process Claim (processClaim(String statusUpdate))**



Nếu statusUpdate null/blank → ném IllegalArgumentException (theo Cách A).



Chỉ cập nhật trạng thái khi trạng thái hiện tại là "Pending":



Đang Pending → update status, return true



Không Pending → không update, return false



Test cases:



Pending → Approved: return true, status đổi.



* Approved rồi → Rejected: return false, status giữ nguyên.
* 
* statusUpdate = null → expect exception.
* 
* statusUpdate = " " → expect exception.



**Feature 3 – Calculate Payout (calculatePayout())**



Nếu status "Approved" → payout = amount \* 0.85



Nếu status khác "Approved" → payout = 0



Test cases:



* Approved với amount 1000 → payout = 850.
* 
* Pending/Rejected → payout = 0.
* 
* Parameterized test cho nhiều trạng thái (Approved/Rejected).
