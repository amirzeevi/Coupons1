package db;

/**
 * Class that contains all queries for the database to be executed.
 * Also contains url and the database port and authorization credentials, to be used by
 * the connection pool for getting a connection from the driver manager.
 */
public class DBmanager {

    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASS = "12345678";

    public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS `coupons_database`";

    public static final String CREATE_COMPANIES_TABLE = "CREATE TABLE IF NOT EXISTS`coupons_database`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons_database`.`customers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `first_name` VARCHAR(45) NOT NULL," +
            "  `last_name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String CREATE_CATEGORIES_TABLE = " CREATE TABLE `coupons_database`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "   PRIMARY KEY (`id`)," +
            "   UNIQUE INDEX `name_UNIQUE` (`name` ASC) INVISIBLE);";

    public static final String CREATE_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons_database`.`coupons` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `company_id` INT NOT NULL," +
            "  `category_id` INT NOT NULL," +
            "  `title` VARCHAR(45) NOT NULL," +
            "  `description` VARCHAR(45) NOT NULL," +
            "  `start_date` DATE NOT NULL," +
            "  `end_date` DATE NOT NULL," +
            "  `amount` INT NOT NULL," +
            "  `price` DOUBLE NOT NULL," +
            "  `image` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`)," +
            "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE," +
            "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE," +
            "  CONSTRAINT `company_id`" +
            "    FOREIGN KEY (`company_id`)" +
            "    REFERENCES `coupons_database`.`companies` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `category_id`" +
            "    FOREIGN KEY (`category_id`)" +
            "    REFERENCES `coupons_database`.`categories` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION)";

    public static final String CREATE_CUSTOMERS_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons_database`.`customers_coupons` (" +
            "  `customer_id` INT NOT NULL," +
            "  `coupon_id` INT NOT NULL," +
            "  PRIMARY KEY (`customer_id`, `coupon_id`)," +
            "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
            "  CONSTRAINT `customer_id`" +
            "    FOREIGN KEY (`customer_id`)" +
            "    REFERENCES `coupons_database`.`customers` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `coupon_id`" +
            "    FOREIGN KEY (`coupon_id`)" +
            "    REFERENCES `coupons_database`.`coupons` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION)";

    public static final String CREATE_TRIGGER_COUPON_PURCHASE = "CREATE TRIGGER coupons_database.coupon_purchase_trigger" +
            " AFTER INSERT ON `coupons_database`.`customers_coupons`" +
            " FOR EACH ROW" +
            " UPDATE `coupons_database`.`coupons` SET `amount` = `amount`-1 WHERE `id` = " +
            "(SELECT `coupon_id` FROM `coupons_database`.`customers_coupons` ORDER BY `coupon_id` DESC LIMIT 1)";

    public static final String SET_TIME_ZONE = "SET GLOBAL time_zone = `+02:00`";

    public static final String DROP_SCHEMA = "DROP SCHEMA `coupons_database`";
    public static final String DROP_COMPANIES_TABLE = "DROP TABLE `coupons_database`.`companies`";
    public static final String DROP_CUSTOMERS_TABLE = "DROP TABLE `coupons_database`.`customers`";
    public static final String DROP_CATEGORIES_TABLE = "DROP TABLE `coupons_database`.`categories`";
    public static final String DROP_COUPONS_TABLE = "DROP TABLE `coupons_database`.`coupons`";
    public static final String DROP_CUSTOMERS_COUPONS_TABLE = "DROP TABLE `coupons_database`.`customers_coupons`";

    public static final String ADD_CATEGORY = "INSERT INTO `coupons_database`.`categories` (`name`) VALUES (?) ";
    public static final String DELETE_CATEGORY = "DELETE FROM `coupons_database`.`categories` WHERE name = ? ";

    public static final String GET_COMPANY_ID = "SELECT id FROM `coupons_database`.`companies` WHERE  email = ? AND password = ? ";
    public static final String IS_COMPANY_EXIST = "SELECT count(*) as counter from `coupons_database`.`companies` WHERE id = ?";
    public static final String IS_COMPANY_NAME_OR_EMAIL_EXIST = "SELECT count(*) as counter FROM `coupons_database`.`companies` WHERE  name = ? OR email = ? ";
    public static final String ADD_COMPANY = "INSERT INTO `coupons_database`.`companies` (`name`, `email`, `password`) VALUES (?,?,?) ";
    public static final String DELETE_COMPANY = "DELETE FROM `coupons_database`.`companies` WHERE id = ? ";
    public static final String UPDATE_COMPANY = "UPDATE `coupons_database`.`companies` SET email = ?, password = ? WHERE id = ? ";
    public static final String UPDATE_COMPANY_IS_EMAIL_EXIST = "SELECT count(*) as counter FROM `coupons_database`.`companies` WHERE id != ? AND email = ?";
    public static final String GET_ALL_COMPANIES = "SELECT*FROM `coupons_database`.`companies` ";
    public static final String GET_ONE_COMPANY = "SELECT*FROM `coupons_database`.`companies` WHERE id = ?";
    public static final String GET_COMPANY_COUPON_CATEGORY = "SELECT * FROM `coupons_database`.`coupons` WHERE category_id = ? AND company_id = ? ";
    public static final String GET_COMPANY_COUPON_MAX_PRICE = "SELECT * FROM `coupons_database`.`coupons` WHERE company_id = ? AND price <= ? ";

    public static final String GET_CUSTOMER_ID = "SELECT id FROM `coupons_database`.`customers` WHERE email = ? AND password = ?";
    public static final String IS_CUSTOMER_EXIST = "SELECT count(*) as counter FROM `coupons_database`.`customers` WHERE id = ?";
    public static final String IS_CUSTOMER_EMAIL_EXISTS = "SELECT count(*) as counter FROM `coupons_database`.`customers` WHERE email = ?";
    public static final String ADD_CUSTOMER = "INSERT INTO `coupons_database`.`customers` (`first_name`,`last_name`, `email`, `password`) VALUES (?,?,?,?) ";
    public static final String DELETE_CUSTOMER = "DELETE FROM `coupons_database`.`customers` WHERE id = ? ";
    public static final String UPDATE_CUSTOMER_IS_EMAIL_EXIST = "SELECT count(*) as counter FROM `coupons_database`.`customers` WHERE id != ? AND email = ?";
    public static final String UPDATE_CUSTOMER = "UPDATE `coupons_database`.`customers` SET first_name = ? ,last_name = ?, email = ?, password = ? WHERE id = ? ";
    public static final String GET_ALL_CUSTOMERS = "SELECT*FROM `coupons_database`.`customers` ";
    public static final String GET_ONE_CUSTOMER = "SELECT*FROM `coupons_database`.`customers` WHERE id = ?";
    public static final String GET_CUSTOMER_COUPON_CATEGORY = "SELECT * FROM `coupons_database`.`coupons` WHERE `category_id` = ? AND id IN (SELECT `coupon_id` FROM `coupons_database`.`customers_coupons` WHERE `customer_id` = ?)";
    public static final String GET_CUSTOMER_COUPON_MAX_PRICE = "SELECT * FROM `coupons_database`.`coupons` WHERE `price` <= ? AND id IN (SELECT `coupon_id` FROM `coupons_database`.`customers_coupons` WHERE `customer_id` = ?)";

    public static final String IS_COMPANY_COUPON_EXISTS = "SELECT count(*) as counter FROM `coupons_database`.`coupons` WHERE company_id = ? AND title = ? ";
    public static final String IS_CUSTOMER_COUPON_EXISTS = "SELECT count(*) as counter FROM `coupons_database`.`customers_coupons` WHERE customer_id = ? AND coupon_id = ? ";
    public static final String ADD_COUPON = "INSERT INTO `coupons_database`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?,?,?,?,?,?,?,?,?) ";
    public static final String DELETE_COUPON = "DELETE FROM `coupons_database`.`coupons` WHERE id = ? ";
    public static final String DELETE_EXPIRED_COUPONS = "DELETE FROM `coupons_database`.`coupons` WHERE end_date < now()";
    public static final String UPDATE_COUPON_IS_TITLE_EXIST = "SELECT count(*) as counter FROM `coupons_database`.`coupons` WHERE id != ? AND company_id = ? AND title = ? ";
    public static final String UPDATE_COUPON = "UPDATE `coupons_database`.`coupons` SET category_id = ?, title = ?, description = ?, start_date = ?, end_date = ?, amount = ?, price = ?, image = ? WHERE id = ? ";
    public static final String GET_ONE_COUPON = "SELECT*FROM `coupons_database`.`coupons` WHERE id = ?";
    public static final String GET_COMPANY_COUPONS = "SELECT*FROM `coupons_database`.`coupons` WHERE  company_id = ?";
    public static final String ADD_COUPON_PURCHASE = "INSERT INTO `coupons_database`.`customers_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?) ";
    public static final String GET_CUSTOMER_COUPONS = "SELECT * FROM `coupons_database`.`coupons` WHERE id IN (SELECT coupon_id FROM `coupons_database`.`customers_coupons` WHERE customer_id = ?)";
}
