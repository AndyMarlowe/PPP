package com.objectmentor.library.web.framework;

import com.objectmentor.library.web.framework.mocks.MockHttpServletRequest;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {
  private MockHttpServletRequest request;
  private ActionResult getResult;
  private ActionResult postResult;
  private TestableController controller;


  protected void setUp() throws Exception {
    getResult = new ActionResult("get");
    postResult = new ActionResult("post");
    controller = new TestableController();
  }

  public void testHandlePost() throws Exception {
    createRequest("POST");
    ActionResult result = controller.handle(request);
    assertSame(postResult, result);
  }

  public void testHandleGet() throws Exception {
    createRequest("GET");
    ActionResult result = controller.handle(request);
    assertSame(getResult, result);
  }

  private void createRequest(String method) {
    request = new MockHttpServletRequest(method);
    request.setAttribute("action_name", "someAction");
  }

  private class TestableController extends Controller {
    public ActionResult someAction() {
      if (isPost()) {
        return postResult;
      } else if (isGet()) {
        return getResult;
      } else {
        return null;
      }
    }

    protected void onHandle() {
    }
  }

}
