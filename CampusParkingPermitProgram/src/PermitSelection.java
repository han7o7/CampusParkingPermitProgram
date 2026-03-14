public final class PermitSelection implements Validatable {

    private final PermitType permitType;
    private final VehicleType vehicleType;
    private final boolean carpool;
    private final int months;

    public PermitSelection(PermitType permitType, VehicleType vehicleType, boolean carpool, int months) {
        this.permitType = permitType;
        this.vehicleType = vehicleType;
        this.carpool = carpool;
        this.months = months;
        validate();
    }

    public PermitType getPermitType() {
        return permitType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isCarpool() {
        return carpool;
    }

    public int getMonths() {
        return months;
    }

    @Override
    public void validate() {
        if (permitType == null) {
            throw new InvalidSelectionException("Permit type cannot be null.");
        }

        if (vehicleType == null) {
            throw new InvalidSelectionException("Vehicle type cannot be null.");
        }

        if (months < 1 || months > 12) {
            throw new InvalidSelectionException("Months must be between 1 and 12.");
        }
    }
}