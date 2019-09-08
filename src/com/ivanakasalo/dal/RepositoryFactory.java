package com.ivanakasalo.dal;

/**
 *
 * @author office10
 */
public final class RepositoryFactory {

    public static IRepository getSqlRepo() {
        return new SqlRepository();
    }

}
