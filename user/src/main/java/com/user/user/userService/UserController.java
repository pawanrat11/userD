package com.user.user.userService;
import com.user.user.models.MY_ADDRESS;
import com.user.user.models.USER;
import com.user.user.service.FirebaseInitializer;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.Acl.User;
import com.sun.el.stream.Stream;





@RestController
public class UserController {
	@Autowired
	FirebaseInitializer db;
	MY_ADDRESS address1;
	MY_ADDRESS address2;
	USER demoUser;
	public UserController() {
		demoUser = new USER();	
		demoUser.setId(1);
		demoUser.setUserName("testUser");
		demoUser.setFirstName("กิตติวัชร");
		demoUser.setLastName("เอี่ยมกิจการ");
		demoUser.setEmail("64070001@it.kmitl.ac.th");
		demoUser.setDareOfBirth(new Date(10, 10, 2020));
		demoUser.setPhone("0889979667");
		demoUser.setGender("Male");
		App.dummyData.add(demoUser);
		
		address1 = new MY_ADDRESS();
		address2 = new MY_ADDRESS();
		address1.setUSER_id(1);
		address1.setMy_address_id(1);
		address1.setNumber("0632128395");
		address1.setPostal_code("10160");
		address1.setDistrict("ปากช่อง");
		address1.setProvince("กรุงดทพ");
		address1.setRoad("พัฒนาการ");
		address1.setSub_district("คันนายาว");
		
		address2.setUSER_id(1);
		address2.setMy_address_id(2);
		address2.setNumber("0631235435");
		address2.setPostal_code("10000");
		address2.setDistrict("ปากเกร็ต");
		address2.setProvince("กรุงดทพ");
		address2.setRoad("อ่อนนุช");
		address2.setSub_district("บางเขน");
		App.dummyDataAddress.add(address1);
		App.dummyDataAddress.add(address2);
		// TODO Auto-generated constructor stub
	}
	
//	@RequestMapping(path = "/createUser", method=RequestMethod.POST)
//	public ResponseEntity<ArrayList<USER>> createUser(@RequestBody USER user) {
//		USER newUser = new USER();	
//		newUser.setUserName(user.getUserName());
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastName(user.getLastName());
//		newUser.setEmail(user.getEmail());
//		newUser.setDareOfBirth(user.getDareOfBirth());
//		newUser.setPhone(user.getPhone());
//		newUser.setGender(user.getGender());
//		App.dummyData.add(newUser);
//		return new ResponseEntity<ArrayList<USER>>(App.dummyData,HttpStatus.OK);
//	}
	
	
//	ได้แล้ว http://localhost:8888/getUserByUsername?username=testUser
	@RequestMapping("/getUserByUsername")
	public USER getUserByUsername(@RequestParam(value="username", defaultValue="") String username) throws InterruptedException, ExecutionException {
//		db get user by userName
	
		//asynchronously retrieve multiple documents
		ApiFuture<QuerySnapshot> future =
		    db.collection("user").whereEqualTo("userName", username).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (DocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(USER.class));
		}
		return demoUser;

	}
//ได้แล้ว http://localhost:8888/getUserByID?id=1 idของdocument 
	@RequestMapping(path = "/getUserByID", method=RequestMethod.GET)
	public USER getUserByID(@RequestParam(value="id", defaultValue="") Integer id) throws InterruptedException, ExecutionException{
//		db get user by id
		String userID = id.toString();
		DocumentReference userRef = db.getFirebase().collection("user").document(userID);
		ApiFuture<DocumentSnapshot> querySnapshot = userRef.get();
		DocumentSnapshot document = null;
		try {
			document = querySnapshot.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (document.exists()) {
		  System.out.println("Document data: " + document.getData());
		  USER u = document.toObject(USER.class);
		  return u;
		} else {
		  System.out.println("No such document!");
		  return null;
		}
		//asynchronously retrieve all documents
//		ApiFuture<QuerySnapshot> future = db.collection("user").get();
//		// future.get() blocks on response
//		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
//		for (QueryDocumentSnapshot document : documents) {
//		  System.out.println(document.getId() + " => " + document.toObject(User.class));
//		}
//		return new ResponseEntity<ArrayList<USER>>(App.dummyData,HttpStatus.OK);
	}
	
	
	//ได้แล้ว getAllUser
	@RequestMapping(path = "/getAllUser", method=RequestMethod.GET)
//	@GetMapping("/getAllUser")  
	public List<USER> getSalesAll() throws InterruptedException, ExecutionException {
		
		List<USER> empList = new ArrayList<USER>();
		CollectionReference userRef= db.getFirebase().collection("user");
		ApiFuture<QuerySnapshot> querySnapshot= userRef.get();
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			USER emp = doc.toObject(USER.class);
			empList.add(emp);
		}
		return empList;
	}

	///groupByGender/
	@RequestMapping(path = "/groupByGender", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<USER>> groupByGender(@RequestParam(value="gender", defaultValue="Male") String gender){
		ArrayList<USER> genderFilter = new ArrayList<USER>();
		USER[] arr = App.dummyData.stream().filter(e -> e.getGender().equals(gender)).toArray(USER[]::new);
		Collections.addAll(genderFilter, arr);
		return new ResponseEntity<ArrayList<USER>>(genderFilter,HttpStatus.OK);
	}
	
	@RequestMapping(path = "/groupByAge/<age_range>", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<USER>> groupByAge(@RequestParam(value="gender", defaultValue="Male") String gender){
		ArrayList<USER> genderFilter = new ArrayList<USER>();
		
		return new ResponseEntity<ArrayList<USER>>(genderFilter,HttpStatus.OK);
	}
	
	///createAddress/
	@RequestMapping(path = "/createAddress", method=RequestMethod.POST)
	public ResponseEntity<ArrayList<MY_ADDRESS>> createAddress(){
		ArrayList<MY_ADDRESS> genderFilter = new ArrayList<MY_ADDRESS>();
		MY_ADDRESS newAddress = new MY_ADDRESS();
		
		genderFilter.add(newAddress);
		return new ResponseEntity<ArrayList<MY_ADDRESS>>(App.dummyDataAddress,HttpStatus.OK);
	}
	
	///getAddress/  ไม่แน่ใจว่ามีคนใช้มั้ย
//	@RequestMapping(path = "/getAddress", method=RequestMethod.GET)
//	public ResponseEntity<ArrayList<USER>> getAddress(@RequestParam(value="gender", defaultValue="Male") String gender){
//		ArrayList<USER> genderFilter = new ArrayList<USER>();
//		
//		return new ResponseEntity<ArrayList<USER>>(genderFilter,HttpStatus.OK);
//	}
	
	///getAddressByID/
		@RequestMapping(path = "/getAddressByUserID", method=RequestMethod.GET)
		public ResponseEntity<ArrayList<MY_ADDRESS>> getAddressByUserID(@RequestParam(value="id", defaultValue="0") Integer id){
			ArrayList<MY_ADDRESS> addressFilter = new ArrayList<MY_ADDRESS>();
			MY_ADDRESS[] arr = App.dummyDataAddress.stream().filter(e -> e.getUSER_id().equals(id)).toArray(MY_ADDRESS[]::new);
			Collections.addAll(addressFilter, arr);
			
			return new ResponseEntity<ArrayList<MY_ADDRESS>>(addressFilter,HttpStatus.OK);
		}
	
	///updateAddress/<my_address_id>
	@RequestMapping(path = "/updateAddress", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<MY_ADDRESS>> updateAddress(@RequestBody MY_ADDRESS updateAddress){
		App.dummyDataAddress.set(App.dummyDataAddress.indexOf(updateAddress), updateAddress);
		return new ResponseEntity<ArrayList<MY_ADDRESS>>(App.dummyDataAddress,HttpStatus.OK);
	}
	
	//deleteAddress/<my_address_id>
	@RequestMapping(path = "/deleteAddress", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<MY_ADDRESS>> deleteAddress(@RequestParam(value="addressID", defaultValue="") Integer addressID){
		ArrayList<MY_ADDRESS> addressFilter = new ArrayList<MY_ADDRESS>();
		MY_ADDRESS[] arr = App.dummyDataAddress.stream().filter(e -> e.getMy_address_id().equals(addressID)).toArray(MY_ADDRESS[]::new);
		Collections.addAll(addressFilter, arr);
		App.dummyDataAddress.remove(addressFilter.get(0));
		
		return new ResponseEntity<ArrayList<MY_ADDRESS>>(App.dummyDataAddress,HttpStatus.OK);
	}
	
}
