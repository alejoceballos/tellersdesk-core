package ac.td.core.action.task.skill.drive;

public enum VehicleModelType {
    // CARS
    COMMUTER_SUV(VehicleType.CAR, 0),
    COMPACT_CAR(VehicleType.CAR, 3),
    DELIVERY_VAN(VehicleType.CAR, -1),
    FULL_SIZE_CAR(VehicleType.CAR, 2),
    FULL_SIZE_VAN(VehicleType.CAR, -1),
    HEAVY_TRUCK(VehicleType.CAR, 0),
    JEEP_WRANGLER(VehicleType.CAR, 1),
    LIGHT_PICKUP(VehicleType.CAR, 1),
    LIMOUSINE(VehicleType.CAR, 0),
    MID_SIZE_CAR(VehicleType.CAR, 2),
    MILITARY_TACTICAL_TRUCK(VehicleType.CAR, 0),
    MINIVAN(VehicleType.CAR, 0),
    MUSCLE_CAR(VehicleType.CAR, 3),
    OFF_ROAD_SUV(VehicleType.CAR, 0),
    PERFORMANCE_MID_SIZE(VehicleType.CAR, 4),
    PERFORMANCE_SUV(VehicleType.CAR, 3),
    RECREATIONAL_VEHICLE(VehicleType.CAR, -1),
    SECURE_SEDAN(VehicleType.CAR, 2),
    SPORT_COMPACT(VehicleType.CAR, 4),
    SPORTS_CAR(VehicleType.CAR, 4),
    SUBCOMPACT_CAR(VehicleType.CAR, 3),
    SUPERCAR(VehicleType.CAR, 5),
    SUV_LIMOUSINE(VehicleType.CAR, -1),

    // MOTORCYCLE
    GENERIC_MOTORCYCLE(VehicleType.MOTORCYCLE, 0),

    // PLANES
    GENERIC_PLANE(VehicleType.PLANE, 0),

    // BOATS
    GENERIC_BOAT(VehicleType.BOAT, 0);

    private final VehicleType type;
    private final int vehicleHandling;

    VehicleModelType(final VehicleType type, final int vehicleHandling) {
        this.type = type;
        this.vehicleHandling = vehicleHandling;
    }

    public VehicleType getType() {
        return type;
    }

    public int getVehicleHandling() {
        return this.vehicleHandling;
    }
}
