package Algo;

public class StatArray implements Statistician{
    private int[] items;
    private int count;
    private int size;
    public StatArray(){
        count = 0;
        size = 10;
        items = new int[size];
    }
    public StatArray(int[] items, int size){
        count = 0;
        this.size = size;
        this.items=items;
    }
    public void Statistician(){
        
    }
    public void setArray(int[] array){
        this.items=array;

    }
    public void setSize(int size){
        this.size=size;
    }
    public int[] getArray(){
        return items;
    }
    public int getSize(){
        return size;
    }
    public void addLast(int elem){
        if(count<size)
            items[count++]=elem;
        else{
            doubleTheSize();
            items[count++]=elem;
        }
    }
    public void add(int elem){
        int i;
        int index= -1;
        if(count<size){
            for(i=0;i<count;i++){
                if(elem<items[i]){
                    index=i;
                    break;
                }
            }
            if(index!=-1){
                for(i=count;i>index;i--){
                    items[i]=items[i-1];
                }
                items[index]=elem;
                count++;
            }else{
                items[count++]=elem;
            }
        }else{
            doubleTheSize();
            add(elem);
        }
    }
    // instead of void display(const char *name,const StatArray s);
    // override to toString() methods
    public void clear(){
        count =0;
    }
    public boolean isEmpty(){
        return this.count == 0;
    }
    public boolean contains(int elem){
        return binarySearch(elem,0,count-1) != -1;

    }
    public int frequency(int elem){
        int ctr=0;
        for(int i =0;i<count;i++){
            if(items[i] == elem)
                ctr++;
        }
        return ctr;
    }
    public int getCount(){
        return count;
    }
    public int findMin(){
        return items[0];
    }
    public int findMax(){
       return items[count-1];
    }
    public int findRange(){
        int first = findMin();
        int last = findMax();
        return last - first;
    }
    public float findMedian(){
        int mid = count/2;
        if(count%2==0)
            return (float)(items[mid]+items[mid-1])/2;
        else{
            return (float)items[mid];
        }
    }
    public int findSum(){
        int sum=0;
        int i;
        for(i=0;i<count;i++){
            sum+=items[i];
        }
        return sum;
    }
    public float findMean(){
        return findSum()/(float)count;
    }
    public float findStandardDeviation(){
        int i;
        float temp;
        float sum=0;
        float mean = findMean();
        float ans;
        for(i=0;i<count;i++){
            temp = items[i]- mean;
            temp*=temp;
            sum+=temp;
        }
        ans = sum/((float)count-1);
        return (float)Math.sqrt(ans);
    }
    //implement remove such that, when you find the item to be removed
    // replace it with the last xent
    public void removeFirst(int x){
        int i = binarySearch(x,0,count-1);
        int j;
        if(i!=-1){
            for(j=i;j<count-1;j++){
                items[j]=items[j+1];
            }
            count--;
        }

    }
    //remove all occurrences, same strategy as removeFirst
    public void removeAll(int x){
        int j, pos = binarySearch(x,0,count);
        while(pos != -1){
            for(j = pos; j < count-1; j++){
                items[j] = items[j+1];
            }
            pos = binarySearch(x,0,count-2);
            count--;
        }
    }
    int binarySearch(int x,int first,int last){
        int mid;
        if (first <= last) {
            mid = (first + last) / 2;
            if (x == items[mid])
                return mid;
            else 
            if (x < items[mid])
                return binarySearch(x,first,mid-1);
            else
                return binarySearch(x,mid+1,last);
        }
        else
            return -1;	
    }
    public int doubleTheSize(){
        size *=2;
        int check = 0;
        int[] arr = new int[size];
        for(int i =0 ; i < count;i++){
            arr[i] = items[i];
        }
        check =  1;
        items = arr;
        return check;
    }
    public String toString() {
        String str="s = [ ";
        for(int i =0;i<count;i++) {
            str+=items[i]+" ";
        }
        str+= "]";
        return str;
    }
}
