rootProject.name = "modular-monolith-with-ddd-kotlin"

include("shared")

include("orders:domain")
include("orders:application")
include("orders:infrastructure")
include("orders:api")

include("customers:domain")
include("customers:application")
include("customers:infrastructure")
include("customers:api")

include("products:domain")
include("products:application")
include("products:infrastructure")
include("products:api")

include("billing:domain")
include("billing:application")
include("billing:infrastructure")
include("billing:api")