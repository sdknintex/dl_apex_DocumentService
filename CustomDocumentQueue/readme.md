CustomDocumentQueue sample
==========================

Demonstrates how to use the **Document Service** class to perform a custom document merge process, by invoking the **convertAndMerge** method on queued document requests.

Overview
--------

This sample is a single file, named CustomDocumentMerge.java, that contains a class named CustomDocumentQueue. The class implements a single method, MergeFiles, in which, given a comma-delimited list of IDs for document requests queued in a custom object named Loop\_\_Document\_Request and settings for the merge process, invokes the convertAndMerge method of the DocumentService class to convert and merge the documents identified by the document requests, and stores the merge status in another custom object, named **MergeStatus**.

Implementing the sample
-----------------------

You need to first implement the **Loop\_\_Document\_Request** and MergeStatus custom objects, and then implement the CustomDocumentQueue Apex class in Salesforce by using the Developer Console.

To implement the sample

1. Log into your Salesforce instance, using a Developer Edition account.

1. In a text editor, open the sample file named CustomDocumentQueue.java.

1. From Setup, create a new custom object named Loop\_\_Document\_Request and, using the sample file as a guide, create the following custom fields:

    Field | Data type
    --- | ---
    Loop\_\_Document\_Id | Text

1. From Setup, create a new custom object named MergeStatus.

1. From your Salesforce instance, open the Developer Console.

    For more information about the Developer Console in Salesforce, see [Developer Console](https://developer.salesforce.com/page/Developer_Console).

1. From the Developer Console, create a new Apex class named CustomDocumentQueue.

1. Paste the contents of the sample file into the Developer Console, overwriting the existing contents.

1. Save the new Apex class, and then close the Developer Console.

Using the sample
----------------

Add one or more records to the Loop\_\_Document\_Request custom object, and then invoke the MergeFiles method of the CustomDocumentQueue class from an external method, such as a custom button or JavaScript script.