package DB;

import javax.print.attribute.standard.PrinterURI;

public class DBmanager {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASS = "12345678";

    public static final String CREATE_SCHEMA = "CREATE SCHEMA `myDataBase`";
    public static final String DROP_SCHEMA = "DROP SCHEMA `myDataBase`";
    public static final String CREATE_COMPANIES_TABLE = "CREATE TABLE IF NOT EXISTS`myDataBase`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String DROP_COMPANIES_TABLE = "DROP TABLE `myDataBase`.`companies`";

    public static final String CREATE_COSTUMERS_TABLE = "CREATE TABLE IF NOT EXISTS `myDataBase`.`costumers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `first_name` VARCHAR(45) NOT NULL," +
            "  `last_name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String DROP_COSTUMERS_TABLE = "DROP TABLE `myDataBase`.`costumers`";

    public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS `myDataBase`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String DROP_CATEGORIES_TABLE = "DROP TABLE `myDataBase`.`categories`";

    public static final String CREATE_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `myDataBase`.`coupons` (" +
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
            "    REFERENCES `myDataBase`.`companies` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `category_id`" +
            "    FOREIGN KEY (`category_id`)" +
            "    REFERENCES `myDataBase`.`categories` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION)";

    public static final String DROP_COUPONS_TABLE = "DROP TABLE `myDataBase`.`coupons`";

    public static final String CREATE_COSTUMERS_COUPONS_TABLE = "CREATE TABLE `myDataBase`.`costumers_coupons` (" +
            "  `costumer_id` INT NOT NULL," +
            "  `coupon_id` INT NOT NULL," +
            "  PRIMARY KEY (`costumer_id`, `coupon_id`)," +
            "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
            "  CONSTRAINT `costumer_id`" +
            "    FOREIGN KEY (`costumer_id`)" +
            "    REFERENCES `myDataBase`.`costumers` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `coupon_id`" +
            "    FOREIGN KEY (`coupon_id`)" +
            "    REFERENCES `myDataBase`.`coupons` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION)";

    public static final String DROP_COSTUMERS_COUPONS_TABLE = "DROP TABLE `myDataBase`.`costumers_coupons`";

    public static final String ADD_CATEGORY = "INSERT INTO `myDataBase`.`categories` (`name`) VALUES (?) ";

    public static final String IS_COMPANY_EXISTS = "SELECT count(*) FROM `myDataBase`.`companies` WHERE  name = ? OR email = ? ";
    public static final String IS_COMPANY_NAME_EXISTS = "SELECT count(*) FROM `myDataBase`.`companies` WHERE `name` = ? ";
    public static final String IS_COMPANY_EMAIL_EXISTS = "SELECT count(*) FROM `myDataBase`.`companies` WHERE `email` = ? ";
    public static final String ADD_COMPANY = "INSERT INTO `myDataBase`.`companies` (`name`, `email`, `password`) VALUES (?,?,?) ";
    public static final String UPDATE_COMPANY = "UPDATE `myDataBase`.`companies` SET email = ?, password = ? WHERE id = ? ";
    public static final String DROP_COMPANY = "DELETE FROM `myDataBase`.`companies` WHERE id = ? ";
    public static final String GET_ALL_COMPANIES = "SELECT*FROM `myDataBase`.`companies` ";
    public static final String GET_ONE_COMPANY = "SELECT*FROM `myDataBase`.`companies` WHERE id = ?";

    public static final String IS_COSTUMER_EMAIL_EXISTS = "SELECT count(*) FROM `myDataBase`.`costumers` WHERE email = ?";
    public static final String IS_COSTUMER_NAME_EXISTS = "SELECT count(*) FROM `myDataBase`.`costumers` WHERE name = ?";
    public static final String ADD_COSTUMER = "INSERT INTO `myDataBase`.`costumers` (`first_name`,`last_name`, `email`, `password`) VALUES (?,?,?,?) ";
    public static final String UPDATE_COSTUMER = "UPDATE `myDataBase`.`costumers` SET first_name = ? ,last_name = ?, email = ?, password = ? WHERE id = ? ";
    public static final String DROP_COSTUMER = "DELETE FROM `myDataBase`.`costumers` WHERE id = ? ";
    public static final String GET_ALL_COSTUMERS = "SELECT*FROM `myDataBase`.`costumers` ";
    public static final String GET_ONE_COSTUMER = "SELECT*FROM `myDataBase`.`costumers` WHERE id = ?";


    public static final String IS_COUPON_EXISTS = "SELECT count(*) FROM `myDataBase`.`coupons` WHERE company_id = ? AND category_id = ? AND title = ? AND description = ? AND start_date = ? AND end_date = ? AND amount = ? AND price = ? AND image = ? ";
    public static final String IS_COUPON_COMPANY_EXISTS = "SELECT count(*) FROM `myDataBase`.`coupons` WHERE company_id = ? AND title = ? ";
    public static final String ADD_COUPON = "INSERT INTO `myDataBase`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?,?,?,?,?,?,?,?,?) ";
    public static final String DELETE_COUPON = "DELETE FROM `myDataBase`.`coupons` WHERE id = ? ";
    public static final String UPDATE_COUPON = "UPDATE `myDataBase`.`coupons` SET category_id = ?, title = ?, description = ?, start_date = ?, end_date = ?, amount = ?, price = ?, image = ? WHERE id = ? ";
    public static final String GET_ONE_COUPON = "SELECT*FROM `myDataBase`.`coupons` WHERE id = ?";
    public static final String GET_ALL_COUPONS = "SELECT*FROM `myDataBase`.`coupons` ";
    public static final String GET_COMPANY_COUPONS = "SELECT*FROM `myDataBase`.`coupons` WHERE  company_id = ?";
    public static final String ADD_COUPON_PURCHASE = "INSERT INTO `myDataBase`.`costumers_coupons` (`costumer_id`, `coupon_id`) VALUES (?, ?) ";
    public static final String DELETE_COUPON_PURCHASE = "DELETE FROM `myDataBase`.`costumers_coupons` WHERE coupon_id = ? OR costumer_id = ? ";
    public static final String GET_COSTUMER_COUPONS = "SELECT*FROM `myDataBase`.`costumers_coupons` WHERE costumer_id = ?";
    public static final String IS_COSTUMER_COUPON_EXISTS = "SELECT count(*) FROM `myDataBase`.`costumers_coupons` WHERE costumer_id = ? AND coupon_id = ? ";

    public static final String COMPANY_LOGIN = "SELECT email, password FROM `myDataBase`.`companies` WHERE id = ?";
    public static final String COSTUMER_LOGIN = "SELECT email, password FROM `myDataBase`.`costumers` WHERE id = ?";
}
