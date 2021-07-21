package com.techqar.protectedtelegrambot;

import org.telegram.telegrambots.api.methods.pinnedmessages.PinChatMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.objects.File;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.Video;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    public int total = 321000;

//    @Override
//    public String getBotUsername() {
//        return "spentsCalculatorbot";
//    }

    @Override
    public String getBotUsername() {
        return "artTestBot_bot";
    }

    @Override
    public String getBotToken() {
        return "826097212:AAF_NrJn2ArvyJb7Ycr0jyVHxM5TiWrpaSA";
    }
//    public String getBotToken() {
//        return "1926506197:AAHIB-Ex69rZANK7xDYp5-kXW0CP7Cj5VAQ";
//    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            if(update.getMessage().getText().equals("/start")) {
                greeting(update);
            } else {

                String chatId = String.valueOf(update.getMessage().getChatId());
                SendMessage message = new SendMessage();
                message.setChatId(chatId);

                String diff = "";

                String spent = update.getMessage().getText();

                String[] row = spent.split("￦");

                if (row.length != 0) {
                    String price = row[0];
                    int p = Integer.parseInt(price);

                    diff = String.valueOf((total - p));
                }

                message.setText(diff);

                PinChatMessage pinChatMessage = new PinChatMessage();
                pinChatMessage.setChatId(chatId);


                try {
                    pinChatMessage(pinChatMessage);
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//            String chatId = String.valueOf(update.getMessage().getChatId());
//
//            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//            message.setChatId(chatId);
//            message.setText(update.getMessage().getText());
//
//            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//            // Create the keyboard (list of keyboard rows)
//            List<KeyboardRow> keyboard = new ArrayList<>();
//            // Create a keyboard row
//            KeyboardRow row = new KeyboardRow();
//            // Set each button, you can also use KeyboardButton objects if you need something else than text
//            row.add("Row 1 Button 1");
//            row.add("Row 1 Button 2");
//            row.add("Row 1 Button 3");
//            // Add the first row to the keyboard
//            keyboard.add(row);
//            // Create another keyboard row
//            row = new KeyboardRow();
//            // Set each button for the second line
//            row.add("Row 2 Button 1");
//            row.add("Row 2 Button 2");
//            row.add("Row 2 Button 3");
//            // Add the second row to the keyboard
//            keyboard.add(row);
//            // Set the keyboard to the markup
//            keyboardMarkup.setKeyboard(keyboard);
//            // Add it to the message
//            message.setReplyMarkup(keyboardMarkup);
//
//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
//            // Message contains photo
//            // Set variables
//            long chat_id = update.getMessage().getChatId();
//
//            // Array with photo objects with different sizes
//            // We will get the biggest photo from that array
//            List<PhotoSize> photos = update.getMessage().getPhoto();
//            // Know file_id
//            String f_id = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getFileId();
//            // Know photo width
//            int f_width = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getWidth();
//            // Know photo height
//            int f_height = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getHeight();
//            // Set photo caption
//            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
//            SendPhoto msg = new SendPhoto();
//            msg.setChatId(String.valueOf(chat_id));
//            msg.setPhoto(f_id);
//            msg.setCaption(caption);
//            try {
//                sendPhoto(msg); // Call method to send the photo with caption
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }

//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//            System.out.println(update.getMessage().getText());
//
//            // Set variables
//            String message_text = update.getMessage().getText();
//            long chat_id = update.getMessage().getChatId();
//            if (message_text.equals("/start")) {
//                // User send /start
//                SendMessage message = new SendMessage() // Create a message object object
//                        .setChatId(chat_id)
//                        .setText(message_text);
//                try {
//                    sendMessage(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else if (message_text.equals("/pic")) {
//                // User sent /pic
//                SendPhoto msg = new SendPhoto()
//                        .setChatId(chat_id)
//                        .setPhoto("AgACAgIAAxkBAANMX-DdxwhCYH5oT0yym4ACzbfAfwUAAsmwMRsm4AABS7DUhLAWL8WHdKh0ly4AAwEAAwIAA3gAA3rKBAABHgQ")
//                        .setCaption("Photo");
//                try {
//                    sendPhoto(msg); // Call method to send the photo
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else if (message_text.equals("/markup")) {
//                SendMessage message = new SendMessage() // Create a message object object
//                        .setChatId(chat_id)
//                        .setText("Here is your keyboard");
//                // Create ReplyKeyboardMarkup object
//                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//                // Create the keyboard (list of keyboard rows)
//                List<KeyboardRow> keyboard = new ArrayList<>();
//                // Create a keyboard row
//                KeyboardRow row = new KeyboardRow();
//                // Set each button, you can also use KeyboardButton objects if you need something else than text
//                row.add("Row 1 Button 1");
//                row.add("Row 1 Button 2");
//                row.add("Row 1 Button 3");
//                // Add the first row to the keyboard
//                keyboard.add(row);
//                // Create another keyboard row
//                row = new KeyboardRow();
//                // Set each button for the second line
//                row.add("Row 2 Button 1");
//                row.add("Row 2 Button 2");
//                row.add("Row 2 Button 3");
//                // Add the second row to the keyboard
//                keyboard.add(row);
//                // Set the keyboard to the markup
//                keyboardMarkup.setKeyboard(keyboard);
//                // Add it to the message
//                message.setReplyMarkup(keyboardMarkup);
//                try {
//                    sendMessage(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else if (message_text.equals("Row 1 Button 1")) {
//                SendPhoto msg = new SendPhoto()
//                        .setChatId(chat_id)
//                        .setPhoto("AgACAgIAAxkBAANMX-DdxwhCYH5oT0yym4ACzbfAfwUAAsmwMRsm4AABS7DUhLAWL8WHdKh0ly4AAwEAAwIAA3gAA3rKBAABHgQ")
//                        .setCaption("Photo");
//                try {
//                    sendPhoto(msg); // Call method to send the photo
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else if (message_text.equals("/hide")) {
//                SendMessage msg = new SendMessage()
//                        .setChatId(chat_id)
//                        .setText("Keyboard hidden");
//                ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
//                msg.setReplyMarkup(keyboardMarkup);
//                try {
//                    sendMessage(msg); // Call method to send the photo
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                // Unknown command
//                SendMessage message = new SendMessage() // Create a message object object
//                        .setChatId(chat_id)
//                        .setText("Unknown command");
//                try {
//                    sendMessage(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
//            // Message contains photo
//            // Set variables
//            long chat_id = update.getMessage().getChatId();
//
//            // Array with photo objects with different sizes
//            // We will get the biggest photo from that array
//            List<PhotoSize> photos = update.getMessage().getPhoto();
//            // Know file_id
//            String f_id = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getFileId();
//            // Know photo width
//            int f_width = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getWidth();
//            // Know photo height
//            int f_height = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getHeight();
//            // Set photo caption
//            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
//            SendPhoto msg = new SendPhoto();
//            msg.setChatId(String.valueOf(chat_id));
//            msg.setPhoto(f_id);
//            msg.setCaption(caption);
//            try {
//                sendPhoto(msg); // Call method to send the photo with caption
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else if(update.hasMessage() && update.getMessage().hasVideo()) {
//            long chat_id = update.getMessage().getChatId();
//
//            Video video = update.getMessage().getVideo();
//            String fileId = video.getFileId();
//
//            String caption = "return video";
//            SendVideo msg = new SendVideo();
//
//            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//            replyKeyboardMarkup.setSelective(false);
//
//            msg.setSupportsStreaming(false);
//
//            msg.setReplyMarkup(replyKeyboardMarkup);
//            msg.setChatId(String.valueOf(chat_id));
//            msg.setVideo(fileId);
//            msg.setCaption(caption);
//            try {
//                sendVideo(msg); // Call method to send the photo with caption
//                downloadFile(new File());
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }



    }

    public void sentPhoto(Update update) {
                    // Message contains photo
            // Set variables
            long chat_id = update.getMessage().getChatId();

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String f_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            // Know photo width
            int f_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getWidth();
            // Know photo height
            int f_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getHeight();
            // Set photo caption
            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
            SendPhoto msg = new SendPhoto();
            msg.setChatId(String.valueOf(chat_id));
            msg.setPhoto(f_id);
            msg.setCaption(caption);
            try {
                sendPhoto(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

    public void greeting(Update update) {
        long chat_id = update.getMessage().getChatId();
        SendMessage msg = new SendMessage();
        msg.setChatId(chat_id);
        msg.setText("Welcome");

        try {
            execute(msg); // Call method to send the photo with caption
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
