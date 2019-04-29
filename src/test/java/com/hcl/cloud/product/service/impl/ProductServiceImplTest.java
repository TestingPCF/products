package com.hcl.cloud.product.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import com.hcl.cloud.product.cache.ProductCacheManager;
import com.hcl.cloud.product.datatranslator.CreateProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.DeleteProductResponseTranslator;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.CreateproductRes;
import com.hcl.cloud.product.response.InventoryQuantityRes;
import com.hcl.cloud.product.response.UpdateproductRes;
import com.hcl.cloud.product.response.ViewproductRes;

public class ProductServiceImplTest {

	ProductServiceImpl productService = new ProductServiceImpl();

	Environment env;

	@Test()
	public void testCreateProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		createproductReq = productService.createProduct(createproductReq, env, txnBean);
		assertEquals("ABC", createproductReq.getSkuCode());
	}
	
	@Test(expected=ProductException.class)
    public void testCreateProductCase2() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        Optional<CreateproductReq> productRequest = Optional.empty();
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");

        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        when(repository.findById("ABC")).thenReturn(productRequest);
        when(repository.save(createproductReq)).thenReturn(createproductReq);
        createproductReq = productService.createProduct(createproductReq, env, txnBean);
        //assertEquals("ABC", createproductReq.getSkuCode());
    }

	@Test(expected = ProductException.class)
	public void testCreateProductExcpetion() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		createproductReq = productService.createProduct(createproductReq, env, txnBean);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test()
	public void testUpdateProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		UpdateproductReq updateproductReq = new UpdateproductReq();
		updateproductReq.setCategory("B");
		updateproductReq.setProductName("MOBILE");
		updateproductReq.setSkuCode("ABC");
		updateproductReq.setProductDescrition("Desc");
		updateproductReq.setListPrice(12);
		updateproductReq.setSalePrice(10);
		createproductReq = productService.updateProduct(updateproductReq, env);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testUpdateProductExcpetion() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		UpdateproductReq updateproductReq = new UpdateproductReq();
		updateproductReq.setCategory("B");
		updateproductReq.setProductName("MOBILE");
		updateproductReq.setSkuCode("ABC");
		createproductReq = productService.updateProduct(updateproductReq, env);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test()
	public void testViewProductByskuCode() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		
		ProductCacheManager productCacheManager = Mockito.mock(ProductCacheManager.class);
		productService.setProductCacheManager(productCacheManager);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);
		String skuCode = "ABC";
		List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
		CreateProductResponseTranslator  createProductResponseTranslator = new CreateProductResponseTranslator();
		createProductResponseTranslator.createproductresponsetranslator(createproductReq, env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test()
    public void testViewProductByskuCodeForCache() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        when(repository.findById("ABC")).thenReturn(productRequest);
        ProductCacheManager productCacheManager = Mockito.mock(ProductCacheManager.class);
        when(productCacheManager.getProductFromCache("ABC")).thenReturn(createproductReq);
        productService.setProductCacheManager(productCacheManager);
        String skuCode = "ABC";
        List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
        assertEquals("ABC", returnList.get(0).getSkuCode());
    }
	
	@Test(expected = ProductException.class)
	public void testViewProductByskuCodeException() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);
		String skuCode = "ABC";
		List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test()
	public void testViewProducts() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		List<CreateproductReq> productRequest = new ArrayList<CreateproductReq>();
		productRequest.add(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findAll()).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		List<CreateproductReq> returnList = productService.viewProducts(env);
		CreateProductResponseTranslator  createProductResponseTranslator = new CreateProductResponseTranslator();
		createproductReq.setStatus("fail");
        createProductResponseTranslator.createproductresponsetranslator(createproductReq, env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testViewProductsException() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		List<CreateproductReq> productRequest = new ArrayList<CreateproductReq>();
		productRequest.add(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findAll()).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		List<CreateproductReq> returnList = productService.viewProducts(env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test()
	public void testDeleteProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(true);
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
		assertEquals("ABC", response.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testDeleteProductException() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(true);
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
		assertEquals("ABC", response.getSkuCode());
	}

	@Test()
	public void testDeleteProductWhenIsDeleteFalse() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(false);
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		ProductCacheManager productCacheManager = Mockito.mock(ProductCacheManager.class);
	    productService.setProductCacheManager(productCacheManager);
		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
		assertEquals("ABC", response.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testCreateFallBack() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(false);
		TransactionBean txBean = new TransactionBean();
		txBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		productService.createProductFallback(createproductReq, env, txBean);
	}

	@Test(expected = ProductException.class)
	public void testDeleteFallBack() throws ProductException {

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		TransactionBean txBean = new TransactionBean();
		txBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		productService.deleteProductFallback(deleteproductReq, env);
	}
	
	
	@Test
	public void testDeleteTranslatorForAreadyDeleted() throws ProductException {
	    
	    CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        env = Mockito.mock(Environment.class);
        createproductReq.setStatus("already");
	    DeleteProductResponseTranslator  deleteProductResponseTranslator = new DeleteProductResponseTranslator();
	    deleteProductResponseTranslator.deleteproductresponseTranslator(createproductReq, env);
	}
	@Test
    public void testDeleteTranslator() throws ProductException {
        
        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        env = Mockito.mock(Environment.class);
        createproductReq.setStatus("change");
        DeleteProductResponseTranslator  deleteProductResponseTranslator = new DeleteProductResponseTranslator();
        deleteProductResponseTranslator.deleteproductresponseTranslator(createproductReq, env);
    }
	
	@Test
    public void testResponse() {
        
       InventoryQuantityRes inventoryQuantityRes = new InventoryQuantityRes();
       inventoryQuantityRes.setActiveStatus(true);
       inventoryQuantityRes.setInStock(true);
       inventoryQuantityRes.setQuantity(2);
       inventoryQuantityRes.setSkuCode("ABC");
       inventoryQuantityRes.getQuantity();
       inventoryQuantityRes.getSkuCode();
       ViewproductRes viewproductRes = new ViewproductRes();
       viewproductRes.setStatus("a");
       viewproductRes.setStatusCode("ABC");
       viewproductRes.setSkuCode("ABC");
       viewproductRes.getSkuCode();
       viewproductRes.getStatus();
       viewproductRes.getStatusCode();
       
       UpdateproductRes updateproductRes = new UpdateproductRes();
       updateproductRes.getSkuCode();
       updateproductRes.getStatus();
       updateproductRes.getStatusCode();
       CreateproductRes createproductRes = new CreateproductRes();
       createproductRes.getSkuCode();
       createproductRes.getStatus();
       createproductRes.getStatusCode();
	}
	
}
