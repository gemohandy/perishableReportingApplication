package datasources;

import java.util.List;

//abstract class contains getMarkers (common through LocalDataSource and NetworkDataSource)
public abstract class DataSource 
{
    public abstract List<Object> getData();
}