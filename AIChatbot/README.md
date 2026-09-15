# JavaBot - AI Chatbot

A simple rule-based AI chatbot built with Java using Swing for the GUI. JavaBot can answer questions about Java, Object-Oriented Programming (OOP), Artificial Intelligence (AI), Natural Language Processing (NLP), and Machine Learning (ML).

## Features

- **Graphical User Interface (GUI)**: Built with Swing for an intuitive user experience
- **FAQ Knowledge Base**: Pre-loaded with common questions and answers about programming and AI
- **Natural Language Processing**: 
  - Text preprocessing (lowercase conversion, punctuation removal)
  - Keyword matching with similarity scoring
- **Special Intents**:
  - Date queries (returns today's date)
  - Time queries (returns current time)
- **Fallback Responses**: Intelligent handling of unknown queries with helpful guidance

## Project Structure

```
AIChatbot/
├── AIChatbot.java    # Main chatbot implementation
└── README.md         # This file
```

## Requirements

- Java 8 or higher
- No external dependencies (uses only Java standard library)

## How to Run

### Compile
```bash
javac AIChatbot.java
```

### Execute
```bash
java AIChatbot
```

The GUI window will open automatically.

## Usage

1. Launch the application
2. Type your question in the input field
3. Press **Send** or **Enter** to submit
4. The chatbot will display its response in the chat area

### Example Queries

- "Hello" → Generic greeting response
- "What is Java?" → Information about Java
- "What is OOP?" → Explanation of Object-Oriented Programming
- "What is AI?" → Definition of Artificial Intelligence
- "What's the date?" → Current date
- "What time is it?" → Current time
- "Help" → List of available topics
- "What's the weather?" → Unknown query fallback response

## Technical Details

### Key Methods

- **`trainBot()`**: Initializes the FAQ knowledge base with predefined Q&A pairs
- **`preprocess(String input)`**: Normalizes user input for better matching
- **`calculateSimilarity(String input, String keyword)`**: Scores keyword matches to find the best FAQ response
- **`getResponse(String userInput)`**: Generates appropriate chatbot response
- **`createGUI()`**: Sets up the Swing GUI interface
- **`main(String[] args)`**: Entry point that initializes the bot and starts the GUI

### Response Generation Algorithm

1. Preprocess user input (lowercase, remove punctuation, trim spaces)
2. Check for exact FAQ match
3. Check for special intents (date/time)
4. Calculate similarity scores against all FAQ keywords
5. Return best matching response or fallback message

## Customization

You can easily extend the chatbot by adding more FAQ entries in the `trainBot()` method:

```java
faq.put("your question", "your answer");
```

## Limitations

- Rule-based approach (not machine learning)
- Limited to predefined FAQ and simple pattern matching
- No context awareness between messages
- No learning from user interactions

## Future Enhancements

- Integration with actual NLP libraries (e.g., Stanford NLP, OpenNLP)
- Machine learning model for better response accuracy
- Conversation context tracking
- Database integration for persistent FAQ management
- Voice input/output capabilities
- Multi-language support

## License

This project is provided as-is for educational purposes.

## Author

Created as a simple chatbot demonstration using Java Swing.