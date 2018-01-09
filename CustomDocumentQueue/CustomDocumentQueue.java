/* This sample shows how to use the Loop.DocumentService class from the Document Queue. */

global class CustomDocumentQueue {
    
    public webService static string MergeFiles(string stringIds) {
        string mergedFilename; // set the name of the combined file
        boolean trackActivity = false; // set whether or not to create a task for tracking status (completion/error)
        string mergeType; // specify the type of merge: pdf, zip, byType (Word+Word, Excel+Excel, PPT+PPT, PDF+PDF) - if not specified, the default is pdf
        Id parentId; // optional - the Id of the Salesforce record to attach the merged file to
        Id workspaceId; // optional - the Content Library Id for where to store the merged document
        Id contentId; // optional - specifies a Content Document to upload a new version of (prefix 069)
        boolean deleteOriginal; // optional - will delete the original documents
        
        List<Id> docRequestIds = stringIds.split(',');
        
        Map<Id, Loop__Document_Request__c> docRequests = new Map<Id, Loop__Document_Request__c>([
            SELECT Id, Loop__Document_Id__c FROM Loop__Document_Request__c WHERE Id IN :docRequestIds AND Loop__Document_Id__c != ''
        ]);
        
        List<Id> docIds = new List<Id>();
        List<Loop__Document_Request__c> docRequests = new List<Loop__Document_Request__c>();
        for (Id docRequestId : docRequestIds) {
            if (docRequests.containsKey(docRequestId)) {
                docIds.add(docRequests.get(docRequestId).Loop__Document_Id__c);
                docRequests.add(new Loop__Document_Request__c(Id = dr.Id, Loop__Status__c = 'Merged'));
            }
        }
        
        MergeStatus__c ms = new MergeStatus__c();
        insert ms;
        parentId = ms.Id;
        
        Map<string, string> params = new Map<string, string> {
            // 'workspaceId' => workspaceId,
            // 'contentId' => contentId,
            //'deleteOrig' => string.valueOf(deleteOriginal),
            'mergeType' => mergeType,
            'trackActivity' => string.valueOf(trackActivity),
            'outputName' => mergedFilename,
            'type' => 'merge'
        };
        
        Id taskId = Loop.DocumentService.convertAndMerge(docIds, parentId, mergedFilename, params);
        
        update docRequests;
        
        if (taskId != null)
            return taskId;
        return parentId;
    }
}