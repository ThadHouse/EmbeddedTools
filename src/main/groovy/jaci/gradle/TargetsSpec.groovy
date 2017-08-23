import org.gradle.api.NamedDomainObjectSet
import org.gradle.model.Managed
import org.gradle.api.Named
import org.gradle.model.ModelMap
import java.util.List

@Managed
interface RemoteTarget extends Named {
    void setAddresses(List<String> address)
    List<String> getAddresses()

    void setConcurrent(boolean runConcurrent)
    boolean getConcurrent()

    void setDirectory(String dir)
    String getDirectory()

    void setUser(String user)
    String getUser()

    void setPassword(String pass)
    String getPassword()

    void setPromptPassword(boolean prompt)
    boolean getPromptPassword()

    void setTimeout(int seconds)
    int getTimeout()

    void setFailOnMissing(boolean tofail)
    boolean getFailOnMissing()
}

@Managed
interface TargetsSpec extends ModelMap<RemoteTarget> { }