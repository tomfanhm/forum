"use client"

import { useRouter } from "next/navigation"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"

import { registerRequest, RegisterRequest } from "@/schemas/auth"
import { register } from "@/lib/auth"

import { Button } from "../ui/button"
import { Card, CardContent, CardFooter } from "../ui/card"
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "../ui/form"
import { Input } from "../ui/input"
import { LoadingSpinner } from "../ui/loading-spinner"
import GoogleLogin from "./google-login"

type RegisterFormProps = {
  redirectUrl?: string
}

const RegisterForm: React.FC<RegisterFormProps> = ({ redirectUrl }) => {
  const router = useRouter()

  const form = useForm<RegisterRequest>({
    defaultValues: {
      email: "",
      password: "",
      confirm_password: "",
    },
    resolver: zodResolver(registerRequest),
  })

  async function onSubmit(values: RegisterRequest) {
    const success = await register(values)
    if (success) {
      router.push("/login")
    }
  }
  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)}>
        <Card className="border-none bg-transparent shadow-none">
          <CardContent>
            <div className="space-y-6">
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Email Address</FormLabel>
                    <FormControl>
                      <Input
                        type="email"
                        placeholder="Email Address"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="password"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Password</FormLabel>
                    <FormControl>
                      <Input
                        type="password"
                        placeholder="Password"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="confirm_password"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Confirm Password</FormLabel>
                    <FormControl>
                      <Input
                        type="password"
                        placeholder="Confirm Password"
                        {...field}
                      />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
              <Button
                type="submit"
                className="w-full"
                disabled={form.formState.isSubmitting}
              >
                {form.formState.isSubmitting ? <LoadingSpinner /> : "Register"}
              </Button>
            </div>
          </CardContent>
          <CardFooter>
            <div className="w-full space-y-6">
              <div className="after:border-border relative text-center text-sm after:absolute after:inset-0 after:top-1/2 after:z-0 after:flex after:items-center after:border-t">
                <span className="bg-card text-muted-foreground relative z-10 px-2">
                  Or continue with
                </span>
              </div>
              <GoogleLogin redirectUrl={redirectUrl} />
            </div>
          </CardFooter>
        </Card>
      </form>
    </Form>
  )
}
export default RegisterForm
