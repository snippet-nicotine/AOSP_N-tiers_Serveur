package message;

import java.time.LocalDate;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.jboss.ejb3.annotation.DeliveryActive;

import potager.entity.Potager;


@MessageDriven (name = "EnvoiPotagerTopic",
	activationConfig = {
		@ActivationConfigProperty( propertyName = "destinationType",
		propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty( propertyName = "destination",
		propertyValue = "jms/topic/TopicPotager"),
		@ActivationConfigProperty( propertyName = "acknowledgeMode",
		propertyValue = "Auto-acknowledge") }
)
@DeliveryActive(true)
public class TopicListenerArrosagePotagers implements MessageListener{

	@Override
	public void onMessage(Message message) {
		
		if(message instanceof TextMessage){
			System.out.println(" [2] Topic: " + LocalDate.now() );
			TextMessage msg = (TextMessage) message;
			try {
				System.out.println(" [2] J'ai arrosé " + msg.getText() + " avec beaucoup d'amouuur" );
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		else if( message instanceof ObjectMessage ){
			
			ObjectMessage msg = (ObjectMessage) message;
			Potager potager  ;
			
			try {
				potager = (Potager) msg.getObject();
				System.out.println("Potager a arroser: " + potager.getNom() );
			} catch (JMSException e) {
				e.printStackTrace();
			}			
		}
		
		else{
			System.out.println("Le message n'est pas valide");
		}
		
	}

}
