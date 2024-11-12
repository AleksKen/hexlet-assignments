package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, String.valueOf(product.getPrice()));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (var connection = dataSource.getConnection();
        var stmp = connection.prepareStatement(sql)) {
            stmp.setLong(1, id);
            var resultSet = stmp.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("title");
                var price = resultSet.getInt("price");
                var product = new Product(name, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        var sql = "SELECT * FROM products";
        try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            var result = new ArrayList<Product>();
            while (resultSet.next()) {
                var name = resultSet.getString("title");
                var price = resultSet.getInt("price");
                var product = new Product(name, price);
                result.add(product);
            }
            return result;
        }
    }

    // END
}
