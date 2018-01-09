/* This sample shows how to use the Loop.DocumentService class from the Document Queue. */

global class CustomDocumentMerge {
    
    public static Id MergeFiles(Id parentRecordId) {
        string mergedFilename; // set the name of the combined file
        boolean trackActivity = false; // set whether or not to create a task for tracking status (completion/error)
        string mergeType; // specify the type of merge: pdf, zip, byType (Word+Word, Excel+Excel, PPT+PPT, PDF+PDF) - if not specified, the default is pdf
        Id parentId; // optional - the Id of the Salesforce record to attach the merged file to
        Id workspaceId; // optional - the Content Library Id for where to store the merged document
        Id contentId; // optional - specifies a Content Document to upload a new version of (prefix 069)
        boolean deleteOriginal; // optional - will delete the original documents
        
        List<Id> docIds = new List<Id>();
        for (Attachment a : [SELECT Id FROM Attachment WHERE ParentId IN (SELECT Id FROM Child__c WHERE Parent__c = :parentRecordId)]) {
            docIds.add(a.Id);
        }
        parentId = parentRecordId;
        
        Map<string, string> params = new Map<string, string> {
            // 'workspaceId' => workspaceId,
            // 'contentId' => contentId,
            // 'deleteOrig' => string.valueOf(deleteOriginal),
            'mergeType' => mergeType,
            'trackActivity' => string.valueOf(trackActivity),
            'outputName' => mergedFilename
        };
        
        Id taskId = Loop.DocumentService.convertAndMerge(docIds, parentId, mergedFilename, params);
        
        update docRequests;
        
        if (taskId != null)
            return taskId;
        return parentId;
    }
}