package com.vyatsu.task14.repositories;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.exceptions.ProductAlreadyExistsException;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.domain.Specification;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vyatsu.task14.repositories.specification.ProductSpecification;
import com.vyatsu.task14.repositories.specification.ListSpecification;

import java.util.Random;
import java.util.List;

@Component
public class ProductRepository {
	private List<Product> products;
	public int pageSize = 10;

	@PostConstruct
	public void init() {
		products = new ArrayList<>();
		Random rnd = new Random();
		String[] categories = {"Smartphone", "Laptop", "Home Appliance", "Tablet", "Smartwatch"};
		String[] brands = {"Samsung", "Apple", "Sony", "LG", "Xiaomi"};

		for (Long i = 1L; i < 101; i++) {
			String category = categories[rnd.nextInt(categories.length)];
			String brand = brands[rnd.nextInt(brands.length)];
			String title = brand + " " + category + " " + i;

			products.add(new Product(i, title, rnd.nextInt(50000) + 1000, 0, category));
		}
	}

	public List<Product> findAll() {

		return products;
	}

	public Product findByTitle(String title) {
		return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
	}

	public Product findById(Long id) {
		return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
	}

	public List<Product> filterProducts(int page, String title, Integer gte, Integer lte) {
		// Создание спецификации для фильтрации
		ListSpecification<Product> spec = ListSpecification.all();

		if (title != null && !title.isEmpty()) {
			spec = spec.and(ProductSpecification.hasTitle(title));
		}
		if (gte != null) {
			spec = spec.and(ProductSpecification.hasPriceGreaterThan(gte));
		}
		if (lte != null) {
			spec = spec.and(ProductSpecification.hasPriceLessThan(lte));
		}

		// Фильтрация по спецификациям
		List<Product> filtered = products.stream()
				.filter(spec::isSatisfiedBy)
				.collect(Collectors.toList());

		// Пагинация
		int from = (page - 1) * pageSize;
		int to = Math.min(from + pageSize, filtered.size());

		if (from > filtered.size()) {
			return List.of();
		}

		return filtered.subList(from, to);
	}



	public int getTotalPages()
	{
		return (int) Math.ceil((double) products.size() / pageSize);
	}

	public void save(Product product) {
		boolean idExists = products.stream()
				.anyMatch(p -> p.getId().equals(product.getId()));

		if (idExists) {
			throw new ProductAlreadyExistsException("Продукт с таким ID уже существует: " + product.getId());
		}

		products.add(product);
	}


	public void delete(Long id) {
		products.stream().filter(p -> p.getId().equals(id)).findFirst().ifPresent(products::remove);
	}

	public void edit(Long id, String title, Integer price) {
		products.stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.ifPresent(product -> {
					product.setTitle(title);
					product.setPrice(price);
				});
	}

	public List<Product> getTop3MostViewedProducts() {
		return products.stream()
				.filter(p -> p.getViewCount() > 0)
			.sorted((p1, p2) -> Integer.compare(p2.getViewCount(), p1.getViewCount()))
			.limit(3)
			.collect(Collectors.toList());
	}
}
