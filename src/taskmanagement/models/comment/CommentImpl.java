package taskmanagement.models.comment;


public class CommentImpl implements  Comment
{
    private String author;
    private String content;

    public CommentImpl(String content, String author)
    {
        setAuthor(author);
        setContent(content);
    }

    @Override
    public String getContent()
    {
        return content;
    }

    @Override
    public String getAuthor()
    {
        return author;
    }

    private void setAuthor(String author)
    {
        this.author = author;
    }

    private void setContent(String content)
    {
        this.content = content;
    }
    @Override
    public String toString()
    {
        return String.format("----------%n" +
                "%s%n" +
                "User: %s%n" +
                "----------%n", getContent(), getAuthor());
    }
}
