import java.sql.Connection
import java.sql.DriverManager

object Database {
    private var connection: Connection? = null

    fun connect() {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:airline.db")
            connection?.let {
                println("Connected to SQLite database.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun createTables() {
        val statement = connection?.createStatement()
        statement?.executeUpdate("""
            CREATE TABLE IF NOT EXISTS users (
                user_id TEXT PRIMARY KEY,
                username TEXT,
                password TEXT,
                email TEXT,
                phone_number INTEGER
            );
            CREATE TABLE IF NOT EXISTS bookings (
                booking_id TEXT PRIMARY KEY,
                user_id TEXT,
                booking_date TEXT,
                total_price REAL,
                status TEXT,
                FOREIGN KEY (user_id) REFERENCES users(user_id)
            );
            CREATE TABLE IF NOT EXISTS routes (
                route_id TEXT PRIMARY KEY,
                departure_location TEXT,
                arrival_location TEXT
            );
            CREATE TABLE IF NOT EXISTS schedules (
                schedule_id TEXT PRIMARY KEY,
                route_id TEXT,
                departure_time TEXT,
                arrival_time TEXT,
                FOREIGN KEY (route_id) REFERENCES routes(route_id)
            );
            CREATE TABLE IF NOT EXISTS booking_details (
                booking_detail_id TEXT PRIMARY KEY,
                booking_id TEXT,
                ticket_id TEXT,
                passenger_id_number INTEGER,
                FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
                FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
                FOREIGN KEY (passenger_id_number) REFERENCES passenger(passenger_id_number)
            );
            CREATE TABLE IF NOT EXISTS payments (
                payment_id TEXT PRIMARY KEY,
                booking_id TEXT,
                payment_date TEXT,
                amount REAL,
                payment_method TEXT,
                status TEXT,
                FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
            );
            CREATE TABLE IF NOT EXISTS tickets (
                ticket_id TEXT PRIMARY KEY,
                schedule_id TEXT,
                armada_name TEXT,
                type TEXT,
                class TEXT,
                seat_capacity INTEGER,
                price REAL,
                FOREIGN KEY (schedule_id) REFERENCES schedules(schedule_id)
            );
            CREATE TABLE IF NOT EXISTS sales (
                sales_id TEXT PRIMARY KEY,
                ticket_id TEXT,
                payment_id TEXT,
                date TEXT,
                total_sales REAL,
                total_book INTEGER,
                total_ticket_sold INTEGER,
                FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),
                FOREIGN KEY (payment_id) REFERENCES payments(payment_id)
            );
            CREATE TABLE IF NOT EXISTS passenger (
                passenger_id_type TEXT,
                passenger_id_number INTEGER PRIMARY KEY,
                passenger_name TEXT,
                passenger_type TEXT,
                seat_number INTEGER
            );
        """.trimIndent())
        statement?.close()
    }

    fun disconnect() {
        connection?.close()
        println("Disconnected from SQLite database.")
    }
}