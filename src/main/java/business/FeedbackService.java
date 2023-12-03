package business;

import application.models.Feedback;
import data.repositories.FeedbackRepository;

public class FeedbackService {

    static FeedbackRepository feedbackRepository = new FeedbackRepository();

    public Feedback submitFeedback(String type, String message, String userEmail) {
        Feedback feedback = new Feedback(type, message, userEmail);
        return feedbackRepository.submitFeedback(feedback);
    }


}
