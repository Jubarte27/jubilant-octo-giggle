public interface Access {
}

public class Compare implements Access {
	public int firstIndex, secondIndex;
	
	public Compare(int fIndex, int sIndex){
		firstIndex = fIndex;
		secondIndex = sIndex;
	}
}


public class Read implements Access {
	public int index;
	
	public Read(int readIndex){
		index = readIndex;
	}
}

public class Swap implements Access {
	public int firstIndex, secondIndex;
	
	public Swap(int fIndex, int sIndex){
		firstIndex = fIndex;
		secondIndex = sIndex;
	}
}

public class Write implements Access {
	public int index;
	
	public Write(int writtenIndex){
		index = writtenIndex;
	}
}
