package app.values;

import java.util.ArrayList;

import app.models.BaseModel;

public class DocRepository {

	private static ArrayList<BaseModel> docRepositoryInstance;

	public static ArrayList<BaseModel> getDocRepositoryInstance() {

		if (docRepositoryInstance == null) {
			docRepositoryInstance = new ArrayList<BaseModel>();
		}

		return docRepositoryInstance;
	}

}
