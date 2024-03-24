package com.example.interviewskeleton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int year;
    private String seoName;
    private String productCode;

    public void generateSeoName() {
        this.seoName = author + " - " + title + " (" + year + ")";
    }

    public void generateProductCode() {
        if (seoName == null) {
            productCode = null;
            return;
        }

        StringBuilder codeBuilder = new StringBuilder();
        for (char ch : seoName.toCharArray()) {
            if (isSeparator(ch)) {
                codeBuilder.append('.');
            } else if (Character.isDigit(ch)) {
                codeBuilder.append(ch);
            } else if (Character.isAlphabetic(ch)) {
                codeBuilder.append(isVowel(ch) ? '1' : '0');
            }
        }
        this.productCode = codeBuilder.toString();
    }

    private boolean isSeparator(char ch) {
        return ch == '(' || ch == ')' || ch == '-' || ch == ' ';
    }

    private boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        return ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
