package at.fhj.swd14.pse.general;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.primefaces.context.ApplicationContext;
import org.primefaces.context.RequestContext;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.CSVBuilder;
import org.primefaces.util.StringEncrypter;
import org.primefaces.util.WidgetBuilder;

/**
 * Created by Edi on 12/11/2016.
 */
public class RequestContextMocker extends RequestContext {
  @Override
  public boolean isAjaxRequest() {
    return false;
  }

  @Override
  public void addCallbackParam(String s, Object o) {

  }

  @Override
  public Map<String, Object> getCallbackParams() {
    return null;
  }

  @Override
  public List<String> getScriptsToExecute() {
    return null;
  }

  @Override
  public void execute(String s) {

  }

  @Override
  public void scrollTo(String s) {

  }

  @Override
  public void update(String s) {

  }

  @Override
  public void update(Collection<String> collection) {

  }

  @Override
  public void reset(String s) {

  }

  @Override
  public void reset(Collection<String> collection) {

  }

  @Override
  public WidgetBuilder getWidgetBuilder() {
    return null;
  }

  @Override
  public AjaxRequestBuilder getAjaxRequestBuilder() {
    return null;
  }

  @Override
  public CSVBuilder getCSVBuilder() {
    return null;
  }

  @Override
  public Map<Object, Object> getAttributes() {
    return null;
  }

  @Override
  public void openDialog(String s) {

  }

  @Override
  public void openDialog(String s, Map<String, Object> map, Map<String, List<String>> map1) {

  }

  @Override
  public void closeDialog(Object o) {

  }

  @Override
  public void showMessageInDialog(FacesMessage facesMessage) {

  }

  @Override
  public ApplicationContext getApplicationContext() {
    return null;
  }

  @Override
  public StringEncrypter getEncrypter() {
    return null;
  }

  @Override
  public void release() {

  }

  @Override
  public boolean isSecure() {
    return false;
  }

  @Override
  public boolean isIgnoreAutoUpdate() {
    return false;
  }

  @Override
  public boolean isRTL() {
    return false;
  }
}
