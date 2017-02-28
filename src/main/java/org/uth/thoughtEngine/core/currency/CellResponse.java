package org.uth.thoughtEngine.core.currency;

import java.util.List;

/**
 * Response object for cell interactions. This object is passed back from a cell in response
 * to an interaction (other cell, human command etc) and packages information specific to the request.
 * To keep it simple a Cell can only respond with lists, individual items and/or scores, where score
 * is always a number from 0 to 100 indicating a percentage.
 * @author Ian Lawson
 */
public class CellResponse 
{
  private String _requestor = null; // ID of requestor if present
  private String _request = null; // Copy of request
  private String _simpleResponse = null; // Textual response from Cell
  private List<String> _data = null; // Response data if present
  private int _responseScore = 0; // Score relating to response
  
  public CellResponse( String requestor, String request, String response, int score )
  {
    _requestor = requestor;
    _request = request;
    _simpleResponse = response;
    _responseScore = score;
  }
  
  public CellResponse( String requestor, String request, String response, List<String> data, int score )
  {
    _requestor = requestor;
    _request = request;
    _simpleResponse = response;
    _data = data;
    _responseScore = score;
  }
  
  public String getRequestor() { return _requestor; }
  public String getRequest() { return _request; }
  public String getSimpleResponse() { return _simpleResponse; }
  public List<String> getData() { return _data; }
  public int getResponseScore() { return _responseScore; }
}
