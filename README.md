# BigPandaEvents

created by Yoav Feldman

Instructions for running the project:
1. Open the file: 'big_panda_events.properties' located in 'resources' folder and specifiy the generator file path (replace the exisiting path with your generator file path).
2. Build the project using maven installer.

This service provides two api calls:
1. GET /get_events_count
Parameter: eventType (String)

Return a count of events by event type.

2. GET /get_word_count
Parameter: word (String)

Return a count of words encountered in the data field of the events.


Improvments:
1. Using queue for counters requests.
2. Change the project architecture to micro-services by seperating each task of the project components.
3. Store the data in a seperated database.
