import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class RecordingList<E extends Comparable<? super E>> extends AbstractList<E> {
	private final ArrayList<E> elements;
	private final ArrayList<E> initialElements;
	private final ArrayList<Access> accesses = new ArrayList<>();
	private boolean recordingAccesses = true;

	@SafeVarargs
	public RecordingList(E... elements) {
		this(List.of(elements));
	}

	public RecordingList(Collection<E> elements) {
		this.elements = new ArrayList<>(elements);
		this.initialElements = (ArrayList<E>) this.elements.clone();
	}

	public ArrayList<Access> getAccesses() {
		return accesses;
	}

	public E get(int index) {
		if (recordingAccesses) {
			accesses.add(new Read(index));
		}
		return elements.get(index);
	}

	public E set(int index, E element) {
		if (recordingAccesses) {
			accesses.add(new Write(index));
		}
		return elements.set(index, element);
	}

	public int size() {
		return elements.size();
	}

	public void swap(int i, int j) {
		if (recordingAccesses) {
			accesses.add(new Swap(i, j));
		}
		E aux = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, aux);
	}

	public int compare(int i, int j) {
		if (recordingAccesses) {
			accesses.add(new Compare(i, j));
		}
		return apply(i, j, Comparable::compareTo);
	}

	private <T> T apply(int i, int j, BiFunction<E, E, T> func) {
		return func.apply(elements.get(i), elements.get(j));
	}

	public void stop() {
		recordingAccesses = false;
	}

	@Override
	public String toString() {
		return  "initialElements: " + Arrays.toString(initialElements.toArray()) + "\n" +
				"currentElements: " + Arrays.toString(elements.toArray()) + "\n" +
				"read: " + accesses.stream().filter(a -> a instanceof Read).count() + "\n" +
				"write: " + accesses.stream().filter(a -> a instanceof Write).count() + "\n" +
				"swap: " + accesses.stream().filter(a -> a instanceof Swap).count() + "\n" +
				"compare: " + accesses.stream().filter(a -> a instanceof Compare).count() + "\n" +
				"accesses:\n" + accesses.stream().map(Objects::toString).collect(Collectors.joining("\n")) + "\n";
	}

	public String getStats(){
		return "read: " + accesses.stream().filter(a -> a instanceof Read).count() + "\n" +
				"write: " + accesses.stream().filter(a -> a instanceof Write).count() + "\n" +
				"swap: " + accesses.stream().filter(a -> a instanceof Swap).count() + "\n" +
				"compare: " + accesses.stream().filter(a -> a instanceof Compare).count() + "\n";
	}
}
