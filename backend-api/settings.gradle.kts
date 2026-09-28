rootProject.name = "backend-api"
include(
    ":gateway",
    ":identity_service",
    ":catalog_service",
    ":lending_service",
    ":notification_service"
)

include("common_lib")