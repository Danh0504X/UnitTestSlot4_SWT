/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danhlo.UnitTest_Slot4;

public class InsuranceClaim {

    private final String claimId;
    private double amount;
    private String claimStatus;

    private static final double PAYOUT_RATE = 0.85;

    /**
     * Constructor to initialize the insurance claim.
     *
     * @param claimId     Claim ID
     * @param claimAmount Amount claimed
     * @throws IllegalArgumentException if claimId null/blank or claimAmount < 0
     */
   public InsuranceClaim(String claimId, double claimAmount) {
    if (claimId == null || claimId.isBlank()) {
        throw new IllegalArgumentException("Claim ID cannot be null or empty");
    }
    if (claimAmount < 0) {
        throw new IllegalArgumentException("claimAmount must be >= 0");
    }

    this.claimId = claimId;
    this.amount = claimAmount;
    this.claimStatus = "Pending";
}


    /**
     * Update status ONLY if current status is Pending.
     *
     * @param statusUpdate New status to apply
     * @return true if updated, false otherwise
     */
    public boolean processClaim(String statusUpdate) {
    if (statusUpdate == null || statusUpdate.isBlank()) {
        throw new IllegalArgumentException("statusUpdate must not be null/blank");
    }

    if ("Pending".equals(this.claimStatus)) {
        this.claimStatus = statusUpdate;
        return true;
    }
    return false;
}

    /**
     * Calculate payout: 85% of amount if Approved, otherwise 0.
     */
    public double calculatePayout() {
        if ("Approved".equals(this.claimStatus)) {
            return this.amount * PAYOUT_RATE;
        }
        return 0.0;
    }

    /**
     * Updates the amount of the claim.
     *
     * @param newAmount new claim amount
     * @throws IllegalArgumentException if newAmount < 0
     */
    public void updateClaimAmount(double newAmount) {
        if (newAmount < 0) {
            throw new IllegalArgumentException("newAmount must be >= 0");
        }
        this.amount = newAmount;
    }

    // Getters
    public String getClaimId() {
        return claimId;
    }

    public double getAmount() {
        return amount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{"
                + "claimId='" + claimId + '\''
                + ", amount=" + amount
                + ", claimStatus='" + claimStatus + '\''
                + '}';
    }
}

