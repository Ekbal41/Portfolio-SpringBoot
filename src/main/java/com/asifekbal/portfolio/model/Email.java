
 
package com.asifekbal.portfolio.model;
 
// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
 
// Class
public class Email {
 
    // Class data members
    private String sender;
    private String body;
    private String subject;
   
}