public class LinkedListDeque <horse>{
    //轮到improvement8了，就像 SLList 的非哨兵版本一样，这会导致代码出现特殊情况，
    // 比我们的第 8 项也是最后一项改进后的代码要难看得多。(你能想到哪些 DLList 方法会有这些特殊情况吗？）
    //最终是要一个泛型的List，为了先做出大体框架，先做int的 double linked List
    public class intNode{
        public horse item;
        //private intNode next;有了双向列表之后，直接把next撤了，有aft不就行了吗
        public intNode pre;
        public intNode aft;
        intNode(intNode z,horse x,intNode y){
            pre = z;
            item = x;
            aft = y;
        }
    }

    //现在是要不要在outer class里面加一个constructor，要加，
    //设计的last是一个指向List最后一个node的节点，和first一样，而不是他的next指向最后一个节点
    //添加了一个哨兵节点
    private intNode sentinel;
    private int size;
    public LinkedListDeque(){
        //sentinel = new intNode(sentinel,(horse)"jo",sentinel);
        sentinel = new intNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.aft = sentinel;
        size = 0;
        size = 0;
    }
    /**public LinkedListDeque(int item){
        first = new intNode(item,first);
        last = first;
        size++;
    }
     */
    public void addFirst(horse item){
        //添加了双向列表之后
        //当列表为空的时候,last需要指向新节点
        //获取首个节点
        intNode head = sentinel.aft;
        intNode temp = new intNode(sentinel,item,head);
        sentinel.aft = temp;
        head.pre = temp;
        size++;
    }
    public void addLast(horse item){
        //添加了双向列表之后
        //list为空的时候,需要和sentinel建立连接，但是list != null的时候，就不需要和sentinl建立连接，而是和last.prev建立连接
        //这两种情况不一样啊
        //找到最后一个节点
        intNode last = sentinel.pre;
        intNode temp = new intNode(last,item,null);
        last.aft = temp;
        sentinel.pre = temp;
        temp.aft = sentinel;
        size++;
    }

    public boolean isEmpty(){
        //如果List不为空，那first必定不是null，否则怎么找到这个List
        //既然有这个method，那就说明我们要能创建空的List，一开始我们是做了一个有参的constructor，所以现在我们需要一个无参的constructor
        //添加了哨兵节点之后，就不能像之前判断了，因为哨兵节点一直都在
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    //看到这儿了
    public void printDeque(){
        //loop
        intNode temp = sentinel.aft;
        while(temp != sentinel){
            System.out.printf(temp.item + " ");
            temp = temp.aft;
        }
    }

    public horse removeFirst(){
        if(size == 0){
            return null;
        }
        //获取首个节点
        intNode head = sentinel.aft;
        horse result = head.item;
        sentinel.aft = head.aft;
        head.aft.pre = sentinel;
        size --;
        return result;
    }

    public horse removeLast(){
        //list为空的时候
        if(isEmpty()){
            return null;//-1是为了int设计的，泛型的话要改了
        }
        intNode last = sentinel.pre;
        horse result = last.item;
        sentinel.pre = last.pre;
        last.pre.aft = sentinel;
        return result;
    }

    public horse get(int index){
        //避免索引越界
        if(index > size - 1 || index<0){
            return null;
        }
        horse result = null;
        intNode temp = sentinel.aft;
        for(int i = -1;i<index;i++){
            result = temp.item;
            temp = temp.aft;
        }
        return result;
    }

    private horse getRecurisiveHelper(intNode current,int index){
        //索引越界
        if(current == sentinel){
            return null;//针对int List 泛型的话需要改
        }
        if(index == 0 ){
            return current.item;
        }
        else{
            return getRecurisiveHelper(current.aft,index-1);
        }
    }
    public horse getRecursive(int index){
        //这个是处理索引无效的情况
        if(index<0){
            return null;
        }
        return getRecurisiveHelper(sentinel.aft,index);
    }


}
