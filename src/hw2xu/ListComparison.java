package original;
import java.util.Random;

/**
 * Class file to run the comparisons of Single Linked Lists and SkipLists.<br>
 * This is Part 3 of the assignment, worth 30 points.<br>
 * Please see below for example output: <br>
 * <pre>
 * {@code
* Comparing insert:
* SkipList insert time: 0.4123 ms
* Number of visited nodes: 1661
* SLL insert time: 0.4481 ms
* Number of visited nodes: 4950
* SkipList level 0: 1->10->14->16->18->19->20->23->28->31->32->32->38->39->40->42->43->43->51->61->63->63->65->68->69->69->71->72->73->83->85->85->86->89->92->98->102->103->104->104->110->112->113->113->115->115->116->117->120->122->124->130->130->130->132->134->135->138->140->144->145->151->154->158->166->168->168->168->169->170->171->175->175->179->181->182->185->186->191->192->192->194->197->201->204->204->206->208->214->216->217->223->226->232->232->234->236->241->241->248->
*              SLL: 169->32->249->183->226->123->164->125->202->128->154->132->239->87->91->20->48->92->165->61->132->209->89->235->114->136->123->200->233->21->162->37->108->195->244->40->180->12->171->122->170->172->225->122->149->186->95->231->146->10->138->2->59->192->185->18->159->239->95->8->132->243->43->57->156->169->213->69->172->26->24->241->52->44->65->210->45->140->26->149->91->193->162->176->26->25->224->131->102->106->236->23->198->109->27->90->206->192->110->116->
* 
* Comparing find:
* SkipList find time: 0.1133 ms
* Number of visited nodes: 1409
* SLL find time: 0.2260 ms
* Number of visited nodes: 8401
* 
* Comparing Delete:
* SkipList delete time: 0.1502 ms
* Number of visited nodes: 1567
* SLL delete time: 0.0411 ms
* Number of visited nodes: 684
* }
 *</pre>
 * @author max
 *
 */
public class ListComparison {
	
	public static void main(String[] args) {
		Random r = new Random();
		SkipList skipList = new SkipList();
		SingleLinkedList SLL = new SingleLinkedList();
		
		//compare insert
		System.out.println("\nComparing insert:");
		long startTime = System.nanoTime();
		for(int i = 0; i<100; i++) {
			skipList.insert(r.nextInt());
		}
		long endTime = System.nanoTime();
		System.out.printf("SkipList insert time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", skipList.getVisitedNodes());
		skipList.resetVisitedNodes();
		startTime = System.nanoTime();
		for(int i = 0; i<100; i++) {
			SLL.insert(r.nextInt());
		}
		endTime = System.nanoTime();
		System.out.printf("SLL insert time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", SLL.getVisitedNodes());
		SLL.resetVisitedNodes();
		
		//compare first levels
		System.out.print("SkipList level ");
		skipList.print(0);
		System.out.print("             SLL: " );
		SLL.print();
		
		//compare find
		System.out.println("\nComparing find:");
		startTime = System.nanoTime();
		for(int i = 0; i<100; i++) {
			skipList.find(r.nextInt());
		}
		endTime = System.nanoTime();
		System.out.printf("SkipList find time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", skipList.getVisitedNodes());
		skipList.resetVisitedNodes();
		startTime = System.nanoTime();
		for(int i = 0; i<100; i++) {
			SLL.find(r.nextInt());
		}
		endTime = System.nanoTime();
		System.out.printf("SLL find time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", SLL.getVisitedNodes());
		SLL.resetVisitedNodes();
		
		//compare delete		
		System.out.println("\nComparing Delete:");
		startTime = System.nanoTime();
		for(int i = 0; i<100; i++) {
			skipList.delete(r.nextInt());
		}
		endTime = System.nanoTime();
		System.out.printf("SkipList delete time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", skipList.getVisitedNodes());
		skipList.resetVisitedNodes();
		startTime = System.nanoTime();
		for(int i = 0; i<100; i++) {
			SLL.delete(r.nextInt());
		}
		endTime = System.nanoTime();
		System.out.printf("SLL delete time: %.04f ms\n", (endTime-startTime)/1000000.0);
		System.out.printf("Number of visited nodes: %d\n", SLL.getVisitedNodes());
		SLL.resetVisitedNodes();
		
		
		//skipList.print();
	}

}
