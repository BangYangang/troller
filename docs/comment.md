| Controller | Method | URL                                                                     | Request Header              | Response                                                        | Request               |
|------------|--------|-------------------------------------------------------------------------|-----------------------------|-----------------------------------------------------------------|-----------------------|
| 댓글 생성      | POST   | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{cards_id}/comment      | Authorization : accessToken | { "status" : 201, "message": "댓글 작성 성공" }                       | { "content": "text" } |
| 댓글 조회      | GET    | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{cards_id}/comment      | Authorization : accessToken | { "status" : 200, "message": "댓글 조회 성공", "data": {responseDto}} |                       |
| 댓글 삭제      | DELETE | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{cards_id}/comment/{id} | Authorization : accessToken | { "status" : 204, "message": "댓글 삭제 성공" }                       |                       |
| 댓글 수정      | PUT    | /api/v1/boards/{board_id}/todos/{todo_id}/cards/{cards_id}/comment/{id} | Authorization : accessToken | { "status" : 200, "message": "댓글 수정 성공" }                       | { "content":"text"}   |
|
