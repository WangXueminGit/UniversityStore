package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.nus.iss.ft6.domain.Category;
import sg.nus.iss.ft6.domain.Product;
import sg.nus.iss.ft6.domain.Vendor;

/*
 * author:Wang Xuemin
 */
public class ProductTest {
	
	private Product product1;
	private Product product2;
	private Category category1;
	private Category category2;
	private Vendor vendor1;
	private Vendor vendor2;
	
	public ProductTest() {
		// TODO Auto-generated constructor stub
		vendor1=new Vendor("vendor1", "a good vendor");
		vendor2=new Vendor("vendor2", "a bad vendor");
		ArrayList<Vendor> vendorList=new ArrayList<>();
		vendorList.add(vendor1);
		vendorList.add(vendor2);
		category1=new Category("CLO","clothes",vendorList);
		category2=new Category("STA","station",vendorList);
		product1=new Product(category1, "product1", "description of product1", 10, 99.0, "98989", 3, 10);
		product2=new Product("CLO/1",category1, "product2", "description of product2", 10, 99.0, "98989", 3, 10);
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetId() {
		assertEquals("CLO/1", product2.getId());
	}
	
	@Test
	public void testSetId() {
		product2.setId("CLO/2");
		assertEquals("CLO/2", product2.getId());
	}
	
	@Test
	public void testGetCategory() {
		assertEquals(category1, product1.getCategory());
	}
	
	@Test
	public void testSetCategory() {
		product1.setCategory(category2);
		assertEquals(category2, product1.getCategory());
	}
	
	@Test
	public void testGetName() {
		assertEquals("product1",product1.getName());
	}
	
	@Test
	public void testSetName() {
		product1.setName("new product1");
		assertEquals("new product1", product1.getName());		
	}
	
	@Test
	public void testGetDescription() {
		assertEquals("description of product1", product1.getDescription());
	}
	
	@Test
	public void testSetDescription() {
		product1.setDescription("new description of product1");
		assertEquals("new description of product1", product1.getDescription());
	}
	
	@Test
	public void testGetQuantity() {
		assertEquals(10, product1.getQuantity());
	}
	
	@Test
	public void testSetQuantity() {
		product1.setQuantity(20);
		assertEquals(20, product1.getQuantity());
	}
	
	@Test
	public void testGetPrice() {
		assertTrue(99.0==product1.getPrice());
	}
	
	@Test
	public void testSetPrice() {
		product1.setPrice(100.0);
		assertTrue(100.0==product1.getPrice());
	}
	
	@Test
	public void testGetBarcodeNumber() {
		assertEquals("98989", product1.getBarcodeNumber());
	}
	
	@Test
	public void testSetBarcodeNumber() {
		product1.setBarcodeNumber("99999");
		assertEquals("99999", product1.getBarcodeNumber());
	}
	
	@Test
	public void testGetThreshold() {
		assertEquals(3, product1.getThreshold());
	}
	
	@Test
	public void testSetThreshold() {
		product1.setThrethold(2);
		assertEquals(2, product1.getThreshold());
	}
	
	@Test
	public void testGetOrderQuantity() {
		assertEquals(10, product1.getOrderQuantity());
	}
	
	@Test
	public void testSetOrderQuantity() {
		product1.setOrderQuantity(20);
		assertEquals(20, product1.getOrderQuantity());
	}
	
	@Test
	public void testCheckBelowThrethold(){
		assertFalse(product1.checkBelowThrethold());
		product1.setQuantity(1);
		assertTrue(product1.checkBelowThrethold());
	}
	
	@Test
	public void testAddQuantity(){
		product1.addQuantity(10);
		assertEquals(20, product1.getQuantity());
	}
	
	@Test
	public void testEqualOfProduct(){
		assertFalse(product1.equalOfProduct(product2));
		product2.setName(product1.getName());
		product2.setDescription(product1.getDescription());
		assertTrue(product1.equalOfProduct(product2));
	}

}
