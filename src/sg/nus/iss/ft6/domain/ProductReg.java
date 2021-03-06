package sg.nus.iss.ft6.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import sg.nus.iss.ft6.dao.ProductDao;

/*
 * ProductReg class:to manage product object
 * Attribute: a list of products and a file operation class
 * Author:Wang Xuemin
 */

public class ProductReg {
	//list of product
	private ArrayList<Product> products;
	//file operation class,used to read productList from file and write productList to file
	private ProductDao pDao;
	//Store is a management class,Store manages many lists.In ProductReg,we have a Store object reference to find category and else.
	private Store store;
	
	//constructor
	public ProductReg(Store store){
		pDao=new ProductDao(store);
	}
	
	//read product list from file
	public void createListFromFile() throws IOException{
		products=pDao.readProductsFromFile();
	}
	
	//write product list to file
	public void writeListToFile() throws IOException{
		pDao.writeProductsToFile(products);
	}
	
	//add a new product,in this method,first step is check if this product already exists,if it exists,just add quantity.
	//if it doesn't exist,generate a new product id,then add this product to products(product list)
	public void addProduct(Category category,String name,String description,int quantity,
			double price,String barcodeNumber,int threshold,int orderQuantity){
		
		//go through the list of product , see if this kind of product has already exist.
		//if the product exists,we just add the quantiry to the product
		
		Product temp = new Product("temp", category, name, description, quantity, price, barcodeNumber, threshold, orderQuantity);
		int i=0;
		for(Product p:products){			
			if(p.equalOfProduct(temp)){
				p.addQuantity(quantity);
				break;
			}
			i++;
		}
		if(i>=products.size()){
			String id=generateProductId(category);
			Product newProduct=new Product(id, category, name, description, quantity, price, barcodeNumber, threshold, orderQuantity);
			products.add(newProduct);
		}
		
	}
	
	//get product list
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	//get a product by product number
	public Product getProductsByBarcodenumber(String barcodenumber){
		
		for(Product p:products){
			if(p.getBarcodeNumber().equals(barcodenumber)){
				return p;
			}
		}
		 return null;
	}
	
	//get a product by product id
	public Product getProductById(String pId){
		for(Product p:products){
			if(p.getId().equals(pId)){
				return p;
			}
		}
		return null;
	}
	
	//remove one product
	public void removeProduct(Product product){
		products.remove(product);
	}
	
	//remove a product by id
	public void removeProductById(String pId){
		removeProduct(getProductById(pId));
	}
	
	//genetate a new product id automatically
	public String generateProductId(Category category){
		/*
		 * Usually,the id is formed by the first three characters of category and the number of product under this categoty plus 1
		 * But if you remove one product,it deletes one productId,for example,there are "CLO/1" "CLO/2" "CLO/3" "CLO/4"
		 * after you remove "CLO/3",there are "CLO/1" "CLO/2" "CLO/4",and the next productId should be "CLO/5"
		*/
		String catcode=category.getCode();
		int num=-1;
		for(Product p:products){
			if(p.getCategory().getCode().equals(category.getCode())){
				num++;
			}
		}
		int id = Integer.parseInt(products.get(num).getId().split("/")[1]);
		String pId=catcode+'/'+(id+1);
		return pId;
	}
	
}
