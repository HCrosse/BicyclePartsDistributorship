public class Part implements Comparable<Part> {

  private String partName;
  private int partNumber;

  Part() {
    partName = "";
    partNumber = -1;
  }

  Part(String[] strings) {
    partName = strings[0];
    partNumber = Integer.parseInt(strings[1]);
  }

  int getPartNumber() {
    return partNumber;
  }

  String getPartName() {
    return partName;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    else if (this == o) {
      return true;
    }
    else {
      Part other = (Part)o;
      return (this.partName.equals(other.partName) || this.partNumber == other.partNumber);
    }
  }

  @Override
  public int compareTo(Part otherPart) {
    return this.partName.compareTo(otherPart.partName);
  }

  @Override
  public String toString() {
    return (partName + "," + partNumber);
  }
}
