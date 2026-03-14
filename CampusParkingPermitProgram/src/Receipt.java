public final class Receipt {

    public String format(PermitSelection selection, PricingResult result) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n===== CAMPUS PARKING RECEIPT =====\n");
        sb.append("Permit Type: ").append(selection.getPermitType()).append("\n");
        sb.append("Vehicle Type: ").append(selection.getVehicleType()).append("\n");
        sb.append("Carpool: ").append(selection.isCarpool() ? "Yes" : "No").append("\n");
        sb.append("Months: ").append(selection.getMonths()).append("\n");
        sb.append("Monthly Rate: $").append(result.getMonthlyRate()).append("\n");
        sb.append("Subtotal: $").append(result.getSubtotal()).append("\n");
        sb.append("Campus Fee (5%): $").append(result.getCampusFee()).append("\n");
        sb.append("Total: $").append(result.getTotal()).append("\n");
        sb.append("==================================\n");

        return sb.toString();
    }
}