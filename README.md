
[![Build Status](https://travis-ci.org/ghacupha/ifris-excel-mapper.svg?branch=master)](https://travis-ci.org/ghacupha/ifris-excel-mapper)

[![](https://jitpack.io/v/ghacupha/ifris-excel-mapper.svg)](https://jitpack.io/#ghacupha/ifris-excel-mapper)

This implementation is used in ifris project to serialize excel data into java objects which can be manipulated
in a java project and persisted into a database for further manipulation and analysis.
Excel mapper is based on the original works of mohsen mahmoudi which can be found on this link https://github.com/mohsen-mahmoudi

- Write code

```java
List<YOUR_MODEL> objects = ExcelMapper.mapFromExcel(EXCEL_FILE)
                .toObjectOf(YOUR_MODEL)
                .fromSheet(SHEET_NUMBER)
                .map();
```

<h3>Example1: Basic Usage</h3>

Model.java
```java
public class Model {

	@Col(name = "first name") // must same excel header (by default is first row)
	private String fistName;
	@Col(name = "last name")
	private String lastName;
	private Integer age; //use wrapper class only (*** not primitive type)
	@Col(name = "birth date", pattern = "dd/MM/yyyy")
	private Date birthdate;
	@Col(index = 4) // must same excel header index (first column index is zero)
	private String fatherName;
	@Col(index = 5)
	private Boolean iq; //use wrapper class only (*** not primitive type)

 	...
	//getter and setter method
}
```
Test
```java
File excelFile = new File(getClass().getResource("/excel.xlsx").getPath());

List<Model> items = ExcelMapper.mapFromExcel(excelFile)
        .toObjectOf(Model.class)
        .fromSheet(0) // if this method not used , called all sheets
        .map();

for (Model item : items) {
    LOG.debug("first name --> {}", item.getFistName());
    LOG.debug("last name --> {}", item.getLastName());
    LOG.debug("age --> {}", item.getAge());
    LOG.debug("birth date --> {}", item.getBirthdate());
    LOG.debug("father name --> {}", item.getFatherName());
    LOG.debug("IQ --> {}", item.getIq());
    LOG.debug("");
}  
```


<h3>Example2: Without Annotation</h3>

Model2.java
```java
public class Model2 {

	private String fistName;
	private String lastName;
	private Integer age;
	private String birthdate;
	private String fatherName;
	private Boolean iq;

 	...
	//getter and setter method
}
```
Test
```java
File excelFile2 = new File(getClass().getResource("/excel2.xlsx").getPath());

Map<String, Integer> fieldToColumnIndex = new HashMap<>(); // map all property of model to index of excel column
fieldToColumnIndex.put("fistName", 0);
fieldToColumnIndex.put("lastName", 1);
fieldToColumnIndex.put("age", 2);
fieldToColumnIndex.put("birthdate", 5);
fieldToColumnIndex.put("fatherName", 8);
fieldToColumnIndex.put("iq", 11);

@Test
    public void loadingTest() throws Exception {

        File excelFile2 = new File(getClass().getResource("/excel2.xlsx").getPath());

        Map<String, Integer> fieldToColumnIndex = new HashMap<>();
        fieldToColumnIndex.put("fistName", 0);
        fieldToColumnIndex.put("lastName", 1);
        fieldToColumnIndex.put("age", 2);
        fieldToColumnIndex.put("birthdate", 5);
        fieldToColumnIndex.put("fatherName", 8);
        fieldToColumnIndex.put("iq", 11);

        List<UnAnnotizedModel> items2 = null;

        try {
            items2 = ExcelMapper.mapFromExcel(new File("D:\\workspaces\\poijiWorkspace\\excel-object-mapper\\src\\main\\resources\\excel2.xlsx"))
                    .toObjectOf(UnAnnotizedModel.class)
                    .fromSheet(0)
                    .mapFieldFrom(fieldToColumnIndex)
                    .map();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = formatter.parse("21/07/1989");

        // using string date. java.util.Date not suported
        Assert.assertEquals("jittagorn",items2.get(0).getFistName());
        Assert.assertEquals("pitakmetagoon",items2.get(0).getLastName());
        Assert.assertEquals(new Integer(25),items2.get(0).getAge());
        Assert.assertEquals("21/07/1989",items2.get(0).getBirthdate());
        Assert.assertEquals("abcd1",items2.get(0).getFatherName());
        Assert.assertEquals(false,items2.get(0).getIq());
    }
```


<h3>Example3: Customization headerRowNum & startRowNum & endRowNum</h3>

Model.java
```java
public class Model {

	@Col(name = "first name") // must same excel header (by default is first row)
	private String fistName;
	@Col(name = "last name")
	private String lastName;
	private Integer age; //use wrapper class only (*** not primitive type)
	@Col(name = "birth date", pattern = "dd/MM/yyyy")
	private Date birthdate;
	@Col(index = 4) // must same excel header index (first column index is zero)
	private String fatherName;
	@Col(index = 5)
	private Boolean iq; //use wrapper class only (*** not primitive type)

 	...
	//getter and setter method
}
```
Test
```java
File excelFile3 = new File(getClass().getResource("/excel3.xlsx").getPath());

List<Model> items3 = ExcelMapper.mapFromExcel(excelFile3)
        .toObjectOf(Model.class)
        .fromSheet(0)
        .headerRowNumber(1)
        .startRowNumber(3)
        .endRowNumber(4)
        .map();

for (Model item : items3) {
    LOG.debug("first name --> {}", item.getFistName());
    LOG.debug("last name --> {}", item.getLastName());
    LOG.debug("age --> {}", item.getAge());
    LOG.debug("birth date --> {}", item.getBirthdate());
    LOG.debug("father name --> {}", item.getFatherName());
    LOG.debug("IQ --> {}", item.getIq());
    LOG.debug("");
}
```


<h3>Example4: Exception Management</h3>

Model.java
```java
public class Model {

	@Col(name = "first name") // must same excel header (by default is first row)
	private String fistName;
	@Col(name = "last name")
	private String lastName;
	private Integer age; //use wrapper class only (*** not primitive type)
	@Col(name = "birth date", pattern = "dd/MM/yyyy")
	private Date birthdate;
	@Col(index = 4) // must same excel header index (first column index is zero)
	private String fatherName;
	@Col(index = 5)
	private Boolean iq; //use wrapper class only (*** not primitive type)

 	...
	//getter and setter method
}
```
Test
```java
File excelFile4 = new File(getClass().getResource("/excel4.xlsx").getPath());

ArrayList<Model> listExceptions = new ArrayList<Model>();

List<Model> items4 = ExcelMapper.mapFromExcel(excelFile4)
		.toObjectOf(Model.class)
		.fromSheet(0)
		.handleCellExceptions() // for manage exception, must called this method first
		.cellExceptionsColor(IndexedColors.YELLOW) // manage color cell
		.getWorkbookExceptions(new WorkbookCallback() { // get workbook that exception shown that
			@Override
			public void getWorkbook(Workbook workbook) throws Throwable {
				// write workbook on disk
				FileOutputStream fileOut = new FileOutputStream(
						getClass().getResource("/").getPath() + "exception.xlsx");
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				LOG.debug("file writed on disk");
			}
		})
		.getAllExceptions(listExceptions) // get all list rows that have exceptions
		.map();

LOG.debug("List Count --> {} ", items4.size());
LOG.debug("Exceptions List Count --> {} ", listExceptions.size());
LOG.debug("");

for (Model item : listExceptions) {
	LOG.debug("exception first name --> {}", item.getFistName());
	LOG.debug("exception last name --> {}", item.getLastName());
	LOG.debug("exception age --> {}", item.getAge());
	LOG.debug("exception birth date --> {}", item.getBirthdate());
	LOG.debug("exception father name --> {}", item.getFatherName());
	LOG.debug("exception IQ --> {}", item.getIq()); // iq data has exception and is null
	LOG.debug("");
}
```

- Good Luck..!
