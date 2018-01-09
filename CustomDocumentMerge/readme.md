CustomDocumentMerge sample
==========================

Demonstrates how to use the DocumentService class to perform a custom document merge process, by invoking the convertAndMerge method.

Overview
--------

This sample is a single file, named CustomDocumentMerge.java, that contains a class named CustomDocumentMerge. The class implements a single method, MergeFiles, in which, given a list of IDs for parent Salesforce records and settings for the merge process, invokes the convertAndMerge method of the DocumentService class to convert and merge the attachments of any child records related to the specified parent records, by querying a custom object named Child.

Implementing the sample
-----------------------

You need to first implement the **Child** custom object, and then implement the CustomDocumentMerge Apex class in Salesforce by using the Developer Console.

To implement the sample

1. Log into your Salesforce instance, using a Developer Edition account.
1. In a text editor, open the sample file named CustomDocumentMerge.java.
1. From Setup, create a new custom object named Child and, using the sample file as a guide, create the following custom fields:

    Field | Data type
    --- | ---
    Parent | Text

1. From your Salesforce instance, open the Developer Console.

    For more information about the Developer Console in Salesforce, see [Developer Console](https://developer.salesforce.com/page/Developer_Console).
1. From the Developer Console, create a new Apex class named CustomDocumentMerge.
1. Paste the contents of the sample file into the Developer Console, overwriting the existing contents.
1. Save the new Apex class, and then close the Developer Console.

Using the sample
----------------

Add one or more records to the Child custom object, and then invoke the MergeFiles method of the CustomDocumentMerge class from an external method, such as a custom button or JavaScript script.